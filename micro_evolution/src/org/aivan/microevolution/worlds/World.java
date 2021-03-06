package org.aivan.microevolution.worlds;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.aivan.microevolution.brains.BrainReport;
import org.aivan.microevolution.food.Food;
import org.aivan.microevolution.food.FoodFactory;
import org.aivan.microevolution.general.Tickable;
import org.aivan.microevolution.lifeforms.LifeForm;
import org.aivan.microevolution.lifeforms.LifeForm.DeathReason;
import org.aivan.microevolution.lifeforms.LifeFormFactory;
import org.aivan.microevolution.predators.Predator;
import org.aivan.microevolution.predators.PredatorFactory;
import org.aivan.microevolution.worlds.points.Point;
import org.aivan.microevolution.worlds.processors.ActionProcessorRunnable;
import org.aivan.microevolution.worlds.processors.DeadLifeFormProcessorRunnable;
import org.aivan.microevolution.worlds.processors.DeadPredatorCheckRunnable;
import org.aivan.microevolution.worlds.processors.LifeFormTickerRunnable;
import org.aivan.microevolution.worlds.processors.PredatorProcessorRunnable;
import org.aivan.microevolution.worlds.processors.PredatorTickerRunnable;
import org.aivan.microevolution.worlds.processors.ReproductionProcessorRunnable;
import org.apache.log4j.Logger;

public abstract class World implements Tickable {

  static final Logger log = Logger.getLogger(World.class);

  private long tickCounter = 0;
  protected LifeFormFactory lifeFormFactory = null;
  protected List<LifeForm> lifeForms = Collections.synchronizedList(new ArrayList<LifeForm>());
  protected FoodFactory foodFactory = null;
  List<Food> food = new ArrayList<Food>();
  List<Point> points = null;
  PredatorFactory predatorFactory = null;
  List<Predator> predators = Collections.synchronizedList(new ArrayList<Predator>());

  // helper stuff
  int threadCount = 4;
  ThreadPoolExecutor tpe = new ThreadPoolExecutor(threadCount, threadCount, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(
      threadCount));

  public void setPredatorFactory(PredatorFactory predatorFactory) {
    this.predatorFactory = predatorFactory;
  }

  // Statistics data:
  // protected List<LifeForm> deadLifeForms = Collections. synchronizedList(new
  // ArrayList<LifeForm>());
  long deadLifeFormCount = 0;
  Map<DeathReason, BigInteger> deathCounters = new HashMap<DeathReason, BigInteger>();

  public long getTickCounter() {
    return tickCounter;
  }

  public FoodFactory getFoodFactory() {
    return foodFactory;
  }

  public void setFoodFactory(FoodFactory foodFactory) {

    log.debug("FoodFactory set: " + foodFactory);

    this.foodFactory = foodFactory;
    this.foodFactory.setWorld(this);
  }

  public List<LifeForm> getLifeForms() {
    return lifeForms;
  }

  @Override
  public void tick() {
    long startTime = System.currentTimeMillis();
    long lastTime = startTime;

    tickCounter++;

    // log.trace("tick, new tickCounter: " + tickCounter);

    // log.trace("ticking foodFactory ...");
    foodFactory.tick();
    lastTime = reportTime(lastTime, "foodFactory tick");

    // log.trace("ticking predators ...");
    List<Predator> predatorCopy = new ArrayList<Predator>(predators);
    int predatorCount = predatorCopy.size();
    int segmentSize = predatorCount / threadCount;
    List<FutureTask<String>> futureTasks = new ArrayList<FutureTask<String>>();
    for (int i = 0; i < threadCount; i++) {
      int segmentStart = i * segmentSize;
      int segmentEnd = (i + 1) * segmentSize;
      if (i == (threadCount - 1)) {
        segmentEnd = predatorCount;
      }
      PredatorTickerRunnable predatorTickerRunnable = new PredatorTickerRunnable(segmentStart, segmentEnd, predatorCopy);
      FutureTask<String> ft = new FutureTask<String>(predatorTickerRunnable, "" + i);
      tpe.execute(ft);
      futureTasks.add(ft);

    }
    waitForFutureTasks(futureTasks);
    lastTime = reportTime(lastTime, "predator tick");

    // log.trace("ticking predatorFactory ...");
    predatorFactory.tick();
    lastTime = reportTime(lastTime, "predatorFactory tick");

    // log.trace("ticking life forms ...");
    int lifeFormCount = lifeForms.size();
    segmentSize = lifeFormCount / threadCount;
    futureTasks.clear();
    for (int i = 0; i < threadCount; i++) {
      int segmentStart = i * segmentSize;
      int segmentEnd = (i + 1) * segmentSize;
      if (i == (threadCount - 1)) {
        segmentEnd = lifeFormCount;
      }
      LifeFormTickerRunnable lifeFormTickerRunnable = new LifeFormTickerRunnable(segmentStart, segmentEnd, lifeForms);
      FutureTask<String> ft = new FutureTask<String>(lifeFormTickerRunnable, "" + i);
      tpe.execute(ft);
      futureTasks.add(ft);
    }
    waitForFutureTasks(futureTasks);
    lastTime = reportTime(lastTime, "life form tick");

    // log.trace("checking for dead life forms...");

    lastTime = deadLifeFormCheck(lastTime, futureTasks);

    // log.trace("Processing lifeform actions ...");

    int pointCount = points.size();
    segmentSize = pointCount / threadCount;
    futureTasks.clear();
    for (int i = 0; i < threadCount; i++) {
      int segmentStart = i * segmentSize;
      int segmentEnd = (i + 1) * segmentSize;
      if (i == (threadCount - 1)) {
        segmentEnd = pointCount;
      }
      ActionProcessorRunnable pointProcessorRunnable = new ActionProcessorRunnable(segmentStart, segmentEnd, points);
      FutureTask<String> ft = new FutureTask<String>(pointProcessorRunnable, "" + i);
      tpe.execute(ft);
      futureTasks.add(ft);
    }
    waitForFutureTasks(futureTasks);
    lastTime = reportTime(lastTime, "actions processing");

    // log.trace("checking for dead life forms...");

    lastTime = deadLifeFormCheck(lastTime, futureTasks);

    // log.trace("checking for dead predators...");

    // System.out.println("predator count:"+predators.size());

    pointCount = points.size();
    segmentSize = pointCount / threadCount;
    futureTasks.clear();
    for (int i = 0; i < threadCount; i++) {
      int segmentStart = i * segmentSize;
      int segmentEnd = (i + 1) * segmentSize;
      if (i == (threadCount - 1)) {
        segmentEnd = pointCount;
      }
      DeadPredatorCheckRunnable deadPredatorCheckRunnable = new DeadPredatorCheckRunnable(segmentStart, segmentEnd, points);
      FutureTask<String> ft = new FutureTask<String>(deadPredatorCheckRunnable, "" + i);
      tpe.execute(ft);
      futureTasks.add(ft);
    }
    waitForFutureTasks(futureTasks);
    lastTime = reportTime(lastTime, "dead predators check");

    // System.out.println("predator count:"+predators.size());

    // log.trace("processing predators...");

    pointCount = points.size();
    segmentSize = pointCount / threadCount;
    futureTasks.clear();
    for (int i = 0; i < threadCount; i++) {
      int segmentStart = i * segmentSize;
      int segmentEnd = (i + 1) * segmentSize;
      if (i == (threadCount - 1)) {
        segmentEnd = pointCount;
      }
      PredatorProcessorRunnable predatorProcessorRunnable = new PredatorProcessorRunnable(segmentStart, segmentEnd, points);
      FutureTask<String> ft = new FutureTask<String>(predatorProcessorRunnable, "" + i);
      tpe.execute(ft);
      futureTasks.add(ft);
    }
    waitForFutureTasks(futureTasks);
    lastTime = reportTime(lastTime, "predator processing");

    // log.trace("checking for dead life forms...");

    lastTime = deadLifeFormCheck(lastTime, futureTasks);

    // log.trace("processing reproduction...");

    pointCount = points.size();
    segmentSize = pointCount / threadCount;
    futureTasks.clear();
    for (int i = 0; i < threadCount; i++) {
      int segmentStart = i * segmentSize;
      int segmentEnd = (i + 1) * segmentSize;
      if (i == (threadCount - 1)) {
        segmentEnd = lifeFormCount;
      }
      ReproductionProcessorRunnable reproductionProcessor = new ReproductionProcessorRunnable(segmentStart, segmentEnd, points, this);
      FutureTask<String> ft = new FutureTask<String>(reproductionProcessor, "" + i);
      tpe.execute(ft);
      futureTasks.add(ft);
    }
    waitForFutureTasks(futureTasks);
    lastTime = reportTime(lastTime, "reproduction processing");

    // log.trace("lifeFormFactory.tick() ...");
    lifeFormFactory.tick();
    lastTime = reportTime(lastTime, "lifeFormFactory ticking");

    lastTime = reportTime(startTime, "tick time");
  }

  private long deadLifeFormCheck(long lastTime, List<FutureTask<String>> futureTasks) {
    int segmentSize;
    int pointCount;
    pointCount = points.size();
    segmentSize = pointCount / threadCount;
    futureTasks.clear();
    for (int i = 0; i < threadCount; i++) {
      int segmentStart = i * segmentSize;
      int segmentEnd = (i + 1) * segmentSize;
      if (i == (threadCount - 1)) {
        segmentEnd = pointCount;
      }
      DeadLifeFormProcessorRunnable deadLifeFormProcessorRunnable = new DeadLifeFormProcessorRunnable(segmentStart, segmentEnd,
          points, this);
      FutureTask<String> ft = new FutureTask<String>(deadLifeFormProcessorRunnable, "" + i);
      tpe.execute(ft);
      futureTasks.add(ft);
    }
    waitForFutureTasks(futureTasks);
    lastTime = reportTime(lastTime, "dead life forms check");
    return lastTime;
  }

  private void waitForFutureTasks(List<FutureTask<String>> futureTasks) {
    for (FutureTask<String> future : futureTasks) {
      try {
        String result = future.get();
        log.trace("FutureTask complted with result: " + result);
      } catch (Exception e) {
        log.error("Error", e);
        throw new RuntimeException("parallel processing failed", e);
      }
    }
  }

  private long reportTime(long startTime, String string) {
    long currentTime = System.currentTimeMillis();

    log.trace(string + ": " + (currentTime - startTime) + " ms");

    return currentTime;
  }

  public List<Point> getPoints() {
    return points;
  }

  public List<Food> getFood() {
    return food;
  }

  public abstract void init();

  public LifeFormFactory getLifeFormFactory() {
    return lifeFormFactory;
  }

  public void setLifeFormFactory(LifeFormFactory lifeFormFactory) {

    log.debug("LifeFormFactory set: " + lifeFormFactory);

    this.lifeFormFactory = lifeFormFactory;
    this.lifeFormFactory.setWorld(this);
  }

  /**
   * Return string representation of the current world's life forms
   * 
   * @return
   */
  public String getLifeFormReport() {
    String result = "";
    int count = 0;
    for (Point point : points) {

      if (count % 30 == 0) {
        result += "\n";
      }

      if (point.getLifeForms().size() == 0) {
        result += " [] ";
      } else {
        result += " [" + point.getLifeForms().size() + "]";
      }
      count++;
    }

    return result;
  }

  public String getFoodReport() {
    String result = "";
    int count = 0;
    for (Point point : points) {

      if (count % 30 == 0) {
        result += "\n";
      }

      if (point.getFood() == null) {
        result += " [] ";
      } else {
        result += " [f]";
      }
      count++;
    }

    return result;
  }

  public String getPredatorReport() {
    String result = "";
    int count = 0;
    for (Point point : points) {

      if (count % 30 == 0) {
        result += "\n";
      }

      if (point.getPredator() == null) {
        result += " [] ";
      } else {
        result += " [p]";
      }
      count++;
    }

    return result;
  }

  public String getLifeFormAndFoodAndPredatorReport() {
    String result = "";
    int count = 0;
    for (Point point : points) {

      if (count % 30 == 0) {
        result += "\n";
      }

      result += " [";

      if (point.getLifeForms().size() == 0) {
        result += " ";
      } else {
        result += point.getLifeForms().size() + "";
      }

      result += "/";

      if (point.getFood() == null) {
        result += " ";
      } else {
        result += "f";
      }

      result += "/";

      if (point.getPredator() == null) {
        result += " ";
      } else {
        result += "p";
      }
      result += "]";

      count++;
    }

    return result;
  }

  public String getBrainReport() {
    BrainReport brainReport = lifeFormFactory.getBrainFactory().createNewBrainReport();

    for (LifeForm lifeForm : lifeForms) {
      brainReport.visit(lifeForm.getBrain());
    }

    return brainReport.getReport();
  }

  public long getDeadLifeFormsCount() {
    return deadLifeFormCount;
  }

  public synchronized void increaseDeadLifeFormCount(DeathReason deathReason) {
    deadLifeFormCount++;
    BigInteger counter = deathCounters.get(deathReason);
    if (counter == null) {
      counter = new BigInteger("0");
    }
    deathCounters.put(deathReason, counter.add(BigInteger.ONE));
  }
  
  

  public Map<DeathReason, BigInteger> getDeathCounters() {
    return deathCounters;
  }

  public List<Predator> getPredators() {
    return predators;
  }

  public void shutdown() throws InterruptedException {
    tpe.shutdown();
    tpe.awaitTermination(100, TimeUnit.SECONDS);
  }
}
