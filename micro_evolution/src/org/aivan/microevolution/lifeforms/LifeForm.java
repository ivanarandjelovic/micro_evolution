package org.aivan.microevolution.lifeforms;

import java.util.List;

import org.aivan.microevolution.brains.Brain;
import org.aivan.microevolution.brains.actions.Action;
import org.aivan.microevolution.food.Food;
import org.aivan.microevolution.general.Tickable;
import org.apache.log4j.Logger;

public abstract class LifeForm implements Tickable {

  static final Logger log = Logger.getLogger(LifeForm.class);

  protected Brain brain = null;

  private long id;

  // Simle counters for statistics
  private long eatenFoodCount = 0;
  private long moveCount = 0;

  // Body state
  private long powerLevel = 0;

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
    log.trace("moved");
    moveCount++;
    powerLevel--;
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
    log.trace("tick...");

    log.trace("ticking brain ...");
    brain.tick();
    powerLevel--;
  }

  public List<Action> getActions() {
    return brain.getActions();
  }

  public boolean isDead() {
    return powerLevel <= 0;
  }

}
