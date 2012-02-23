package org.aivan.microevolution.worlds.points;

import java.util.HashSet;
import java.util.Set;

import org.aivan.microevolution.food.Food;
import org.aivan.microevolution.lifeforms.LifeForm;
import org.aivan.microevolution.worlds.World;
import org.apache.log4j.Logger;

/**
 * Abstract class for world points. They belong to a world, and have a set of
 * life forms on them. Also, there can be food on it.
 * 
 * @author aivan
 * 
 */
public abstract class Point {

  static final Logger log = Logger.getLogger(Point.class);

  World world = null;

  Set<LifeForm> lifeForms = new HashSet<LifeForm>();;

  Food food = null;

  Point(World world) {
    super();
    this.world = world;
  }

  public void lifeFormEntered(LifeForm lifeForm) {
    log.trace("Life form entetered: " + lifeForm);
    lifeForms.add(lifeForm);
  }

  public void lifeFormLeft(LifeForm lifeForm) {
    log.trace("Life form left: " + lifeForm);
    lifeForms.remove(lifeForm);
  }

  public Food getFood() {
    return food;
  }

  public Set<LifeForm> getLifeForms() {
    return lifeForms;
  }

  public void addFood(Food food) {

    log.trace("Food added" + food);

    if (this.food == null) {
      this.food = food;
      world.getFood().add(food);
    } else {
      throw new RuntimeException("Food already exists on this point!");
    }
  }

  public Food removeFood() {
    Food eatenFood = this.food;

    log.trace("Food removed" + eatenFood);

    if (this.food != null) {
      world.getFood().remove(this.food);
      this.food = null;
    } else {
      throw new RuntimeException("Food does not exists here!");
    }
    return eatenFood;
  }

}
