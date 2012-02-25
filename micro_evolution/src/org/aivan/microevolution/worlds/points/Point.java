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
  long id;
  Point next = null;

  Set<LifeForm> lifeForms = new HashSet<LifeForm>();;

  Food food = null;

  Point(long id, World world, Point next) {
    super();
    this.world = world;
    this.id = id;
    this.next = next;
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

  public void setFood(Food food) {

    log.trace("Food added" + food);

    if (this.food == null) {
      world.getFood().add(food);
      this.food = food;
    } else if (food == null) {
      world.getFood().remove(this.food);
      this.food = food;
    } else {
      throw new RuntimeException("Food already exists on this point or null food set already!");
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

  @Override
  public String toString() {
    return this.getClass().getSimpleName() + "/" + id;
  }

  public Point getNext() {
    return next;
  }

  public void setNext(Point next) {
    this.next = next;

  }
}
