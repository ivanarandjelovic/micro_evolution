package org.aivan.microevolution.worlds;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadPoolExecutor;

import org.aivan.microevolution.brains.actions.Action;
import org.aivan.microevolution.brains.actions.EatAction;
import org.aivan.microevolution.brains.actions.MoveAction;
import org.aivan.microevolution.food.Food;
import org.aivan.microevolution.food.FoodFactory;
import org.aivan.microevolution.general.Tickable;
import org.aivan.microevolution.lifeforms.LifeForm;
import org.aivan.microevolution.lifeforms.factories.LifeFormFactory;
import org.aivan.microevolution.lifeforms.predators.Predator;
import org.aivan.microevolution.lifeforms.predators.PredatorFactory;
import org.aivan.microevolution.worlds.points.Point;
import org.apache.log4j.Logger;

public abstract class World implements Tickable {

  static final Logger log = Logger.getLogger(World.class);

  private long tickCounter = 0;
  protected LifeFormFactory lifeFormFactory = null;
  protected List<LifeForm> lifeForms = new ArrayList<LifeForm>();
  protected FoodFactory foodFactory = null;
  List<Food> food = new ArrayList<Food>();
  List<Point> points = null;
  PredatorFactory predatorFactory = null;
  List<Predator> predators = new ArrayList<Predator>();

  public void setPredatorFactory(PredatorFactory predatorFactory) {
    this.predatorFactory = predatorFactory;
  }

  // Statistics data:
  protected List<LifeForm> deadLifeForms = new ArrayList<LifeForm>();

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

    //log.trace("tick, new tickCounter: " + tickCounter);

    //log.trace("ticking foodFactory ...");
    foodFactory.tick();

    lastTime = reportTime(lastTime, "foodFactory.tick();");
    
    //log.trace("ticking predators ...");
    for (Predator predator : predators) {
      predator.tick();
    }

    lastTime = reportTime(lastTime, "predator.tick();");

    //log.trace("ticking predatorFactory ...");
    predatorFactory.tick();

    lastTime = reportTime(lastTime, "predatorFactory.tick();");

    //log.trace("ticking life forms ...");
    for (LifeForm lifeForm : lifeForms) {
      lifeForm.tick();
    }

    lastTime = reportTime(lastTime, "lifeForm.tick();");

    //log.trace("Processing lifeform actions ...");

    for (Point point : points) {
      //log.trace("processing point: " + point);
      // We need a copy of a set due to concurrent updated while iterating
      Set<LifeForm> lifeFormsCopy = new HashSet<LifeForm>(point.getLifeForms());
      for (LifeForm lifeForm : lifeFormsCopy) {
        List<Action> actions = lifeForm.getActions();
        if (actions.isEmpty()) {
          //log.trace("lifeform: " + lifeForm + " no actions.");
        } else {
          for (Action action : actions) {
            if (action instanceof EatAction) {
              //EatAction eatAction = (EatAction) action;
              //log.trace("lifeform: " + lifeForm + " eating: " + eatAction);
              if (point.getFood() != null) {
                lifeForm.eat(point.getFood());
                point.setFood(null);
              }
            } else if (action instanceof MoveAction) {
              //MoveAction moveAction = (MoveAction) action;
              //log.trace("lifeform: " + lifeForm + " moving: " + moveAction);
              point.lifeFormLeft(lifeForm);
              point.getNext().lifeFormEntered(lifeForm);
              lifeForm.moved();
            } else {
              throw new RuntimeException("Unknown action type!?: " + action);
            }
          }
        }
      }
    }

    lastTime = reportTime(lastTime, "processing actions.");

    //log.trace("checking for dead life forms...");

    for (Point point : points) {
      //log.trace("processing point: " + point);
      // We need a copy of a set due to concurrent updated while iterating
      Set<LifeForm> lifeFormsCopy = new HashSet<LifeForm>(point.getLifeForms());
      for (LifeForm lifeForm : lifeFormsCopy) {
        if (lifeForm.isDead()) {
          point.lifeFormLeft(lifeForm);
          lifeForms.remove(lifeForm);
          deadLifeForms.add(lifeForm);
        }
      }
    }

    lastTime = reportTime(lastTime, "dead life forms");

    //log.trace("checking for dead predators...");

    for (Point point : points) {
      //log.trace("processing point: " + point);
      Predator predator = point.getPredator();
      if (predator != null && predator.isDead()) {
        point.setPreadator(null);
      }
    }

    lastTime = reportTime(lastTime, "dead predators");

    //log.trace("processing predators...");

    for (Point point : points) {
      //log.trace("processing point: " + point);
      if (point.getPredator() != null && point.getLifeForms().size() > 0) {
        Predator predator = point.getPredator();
        predator.meet(point.getLifeForms().get(0));
      }
    }

    lastTime = reportTime(lastTime, "processing predators");

    //log.trace("checking for dead life forms...");

    for (Point point : points) {
      //log.trace("processing point: " + point);
      // We need a copy of a set due to concurrent updated while iterating
      Set<LifeForm> lifeFormsCopy = new HashSet<LifeForm>(point.getLifeForms());
      for (LifeForm lifeForm : lifeFormsCopy) {
        if (lifeForm.isDead()) {
          point.lifeFormLeft(lifeForm);
          lifeForms.remove(lifeForm);
          deadLifeForms.add(lifeForm);
        }
      }
    }
    
    lastTime = reportTime(lastTime, "dead life forms");

    long endTime = System.currentTimeMillis();
    log.trace("tick time: "+(endTime - startTime)+ " ms");
  }

  private long reportTime(long startTime, String string) {
    long currentTime = System.currentTimeMillis();
    
    log.trace(string+": "+(currentTime-startTime)+" ms");
    
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

  public List<LifeForm> getDeadLifeForms() {
    return deadLifeForms;
  }

  public List<Predator> getPredators() {
    return predators;
  }

}