package org.aivan.microevolution.lifeforms;

import java.util.List;

import org.aivan.microevolution.brains.Brain;
import org.aivan.microevolution.brains.actions.Action;
import org.aivan.microevolution.food.Food;
import org.aivan.microevolution.general.Tickable;
import org.aivan.microevolution.worlds.points.Point;
import org.apache.log4j.Logger;

public abstract class LifeForm implements Tickable {

  static final Logger log = Logger.getLogger(LifeForm.class);

  protected Brain brain = null;

  private long id;
  private int diedFrom = 0;

  // Simple counters for statistics
  private long eatenFoodCount = 0;
  private long moveCount = 0;

  // Body state
  private long powerLevel = 0;

  // Helper stuff
  Point locatonPoint = null;

  public LifeForm(long id, Brain brain, long initialPowerLevel) {
    super();
    this.id = id;
    this.brain = brain;
    this.powerLevel = initialPowerLevel;
  }

  @Override
  public String toString() {
    return this.getClass().getSimpleName() + "/" + id;
  }

  public void moved() {
    // log.trace("moved");
    moveCount++;
    powerLevel--;
    checkIfDiedFromHunger();
  }

  private void checkIfDiedFromHunger() {
    if (powerLevel == 0) {
      diedFrom = 2;
    }
  }

  public void eat(Food food) {
    // TODO Auto-generated method stub
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
    // log.trace("tick...");

    // Provide hunger level
    double hungerStrength = 0.01 * (100-powerLevel);
    if(hungerStrength<0) {
      hungerStrength = 0.0;
    }
    brain.hunger(hungerStrength);
    
    // log.trace("ticking brain ...");
    brain.tick();
    powerLevel--;
    checkIfDiedFromHunger();
  }

  public List<Action> getActions() {
    return brain.getActions();
  }

  public boolean isDead() {
    return powerLevel <= 0;
  }

  public void kill() {
    this.powerLevel = 0;
    diedFrom = 1;
  }

  public int getDiedFrom() {
    return diedFrom;
  }

  public Point getLocatonPoint() {
    return locatonPoint;
  }

  public void setLocatonPoint(Point locatonPoint) {
    this.locatonPoint = locatonPoint;
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

}
