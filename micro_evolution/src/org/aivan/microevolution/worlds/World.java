package org.aivan.microevolution.worlds;

import java.util.ArrayList;
import java.util.List;

import org.aivan.microevolution.brains.actions.Action;
import org.aivan.microevolution.brains.actions.EatAction;
import org.aivan.microevolution.brains.actions.MoveAction;
import org.aivan.microevolution.food.Food;
import org.aivan.microevolution.food.FoodFactory;
import org.aivan.microevolution.general.Tickable;
import org.aivan.microevolution.lifeforms.LifeForm;
import org.aivan.microevolution.lifeforms.factories.LifeFormFactory;
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

    tickCounter++;

    log.trace("tick, new tickCounter: " + tickCounter);

    log.trace("ticking foodFactory ...");
    foodFactory.tick();

    log.trace("ticking life forms ...");
    for (LifeForm lifeForm : lifeForms) {
      lifeForm.tick();
    }

    log.trace("Processing lifeform actions ...");
    for (LifeForm lifeForm : lifeForms) {
      List<Action> actions = lifeForm.getActions();
      if (actions.isEmpty()) {
        log.trace("lifeform: " + lifeForm + " no actions.");
      } else {
        for (Action action : actions) {
          if (action instanceof EatAction) {
            EatAction eatAction = (EatAction) action;
            // TODO: implement eat:
            log.trace("lifeform: " + lifeForm + " eating: " + eatAction);
          } else if (action instanceof MoveAction) {
            MoveAction moveAction = (MoveAction) action;
            // TODO: implement move:
            log.trace("lifeform: " + lifeForm + " moving: " + moveAction);
          } else {
            throw new RuntimeException("Unknown action type!?: " + action);
          }
        }
      }
    }

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

  public String getLifeFormAndFoodReport() {
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

      result += "]";
      count++;
    }

    return result;
  }

}