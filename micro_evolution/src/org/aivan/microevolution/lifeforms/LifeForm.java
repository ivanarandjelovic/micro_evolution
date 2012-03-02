package org.aivan.microevolution.lifeforms;

import java.util.List;

import org.aivan.microevolution.actions.Action;
import org.aivan.microevolution.brains.Brain;
import org.aivan.microevolution.food.Food;
import org.aivan.microevolution.general.Tickable;
import org.aivan.microevolution.worlds.points.Point;
import org.apache.log4j.Logger;

public class LifeForm implements Tickable {

  static final Logger log = Logger.getLogger(LifeForm.class);

  public enum DeathReason {
    PREDATOR, HUNGER, AGE
  }

  private LifeFormFactory lifeFormFactory = null;
  protected Brain brain = null;
  private long maxAge = 0;
  private long minimumReproductionPowerLevel = 0;
  private long reproductionPowerCost = 0;
  private long id;
  private DeathReason diedFrom = null;

  // Simple counters for statistics
  private long eatenFoodCount = 0;
  private long moveCount = 0;

  // Body state
  private long powerLevel = 0;
  private long age = 0;

  // Helper stuff
  Point locatonPoint = null;

  public LifeForm(LifeFormFactory lifeFormFactory, long id, Brain brain, long initialPowerLevel, long maxAge,
      long minimumReproductionPowerLevel, long reproductionPowerCost) {
    super();
    this.id = id;
    this.brain = brain;
    this.powerLevel = initialPowerLevel;
    this.maxAge = maxAge;
    this.minimumReproductionPowerLevel = minimumReproductionPowerLevel;
    this.reproductionPowerCost = reproductionPowerCost;
    this.lifeFormFactory = lifeFormFactory;
  }

  @Override
  public String toString() {
    return this.getClass().getSimpleName() + "/" + id;
  }

  public void moved() {
    // log.trace("moved");
    moveCount++;
    powerLevel--;
    checkIfDied();
  }

  private void checkIfDied() {
    if (powerLevel <= 0) {
      diedFrom = LifeForm.DeathReason.HUNGER;
    } else if (age > maxAge) {
      diedFrom = LifeForm.DeathReason.AGE;
    }
  }

  public void eat(Food food) {
    eatenFoodCount++;
    powerLevel += food.getPowerLevel();
  }

  public String getReport() {
    String report = "";

    report += "LifeForm id: " + id;
    report += "\npowerLevel: " + powerLevel;
    report += "\neatenFoodCount: " + eatenFoodCount;
    report += "\nmoveCount: " + moveCount;

    return report;
  }

  @Override
  public void tick() {
    if (isDead()) {
      throw new RuntimeException("ticking dead life form! " + this);
    }
    // log.trace("tick...");

    // Provide hunger level
    double hungerStrength = 0.01 * (100 - powerLevel);
    if (hungerStrength < 0) {
      hungerStrength = 0.0;
    }
    brain.hunger(hungerStrength);

    // log.trace("ticking brain ...");
    brain.tick();
    powerLevel--;
    age++;
    checkIfDied();
  }

  public List<Action> getActions() {
    return brain.getActions();
  }

  public boolean isDead() {
    return (diedFrom != null);
  }

  public void kill() {
    this.powerLevel = 0;
    diedFrom = LifeForm.DeathReason.PREDATOR;
  }

  public DeathReason getDiedFrom() {
    return diedFrom;
  }

  public Point getLocatonPoint() {
    return locatonPoint;
  }

  public void setLocatonPoint(Point locatonPoint) {
    this.locatonPoint = locatonPoint;
  }

  public long getAge() {
    return age;
  }

  public long getPowerLevel() {
    return powerLevel;
  }

  public boolean canReproduce() {
    return powerLevel > minimumReproductionPowerLevel;
  }

  public void reproductionDone() {
    powerLevel -= reproductionPowerCost;
  }

  // INPUTS:

  public void foodInSight(double signalStrength) {
    brain.foodInSight(signalStrength);
  }

  public void predatorInSight(double signalStrength) {
    brain.predatorInSight(signalStrength);
  }

  public void lifeFormInSight(double signalStrength) {
    brain.lifeFormInSight(signalStrength);
  }

  public LifeForm reproduceWith(LifeForm pairForm) {
    Brain newBrain = brain.combineWith(pairForm.getBrain());
    LifeForm newLifeForm = new LifeForm(lifeFormFactory, lifeFormFactory.getNextLifeFormCounter(), newBrain,
        (long) (reproductionPowerCost * 1.5) , maxAge, minimumReproductionPowerLevel, reproductionPowerCost);
    
    reproductionDone();
    pairForm.reproductionDone();
    
    return newLifeForm;
  }

  private Brain getBrain() {
    return brain;
  }

}
