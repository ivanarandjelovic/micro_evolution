package org.aivan.microevolution.food;

import java.util.List;

import org.aivan.microevolution.worlds.points.Point;
import org.apache.log4j.Logger;

public class ConstantFoodFactory extends FoodFactory {

  static final Logger log = Logger.getLogger(ConstantFoodFactory.class);

  public static final double INITIAL_FOOD_COUNT_PERCENT = 5.0;
  public static final double FOOD_PER_POINT_PER_TICK = 0.05;
  public static final double MAX_POINT_WITH_FOOD_PERCENT = 25;

  private double currentFoodFactor = 0.0;

  public ConstantFoodFactory() {
    log.trace("Created");
  }

  @Override
  public void tick() {

    log.trace("tick...");

    List<Point> points = world.getPoints();
    long pointCount = points.size();

    currentFoodFactor += (pointCount * FOOD_PER_POINT_PER_TICK);

    if (currentFoodFactor >= 1.0) {
      log.trace("Creating new food(s) ...");
      while (currentFoodFactor >= 1.0) {
        if (pointCount / 100.0 * world.getFood().size() < MAX_POINT_WITH_FOOD_PERCENT) {
          log.trace("Creating new food.");
          generateOneFood(points, pointCount);
        } else {
          log.trace("Too much food exists, skippig.");
        }
        currentFoodFactor -= 1.0;
      }
    }
  }

  @Override
  public void init() {
    log.trace("init...");

    List<Point> points = world.getPoints();
    long pointCount = points.size();

    log.trace("Generating initial food(s)...");

    while (pointCount / 100.0 * world.getFood().size() < INITIAL_FOOD_COUNT_PERCENT) {
      log.trace("Generating initial food.");
      generateOneFood(points, pointCount);
    }

  }

  private void generateOneFood(List<Point> points, long pointCount) {
    Food food = new Food(getNextFoodCounter());
    int rand = (int) Math.floor(Math.random() * pointCount);
    Point point = points.get(rand);
    if (point.getFood() == null) {
      log.trace("Point has no food, adding it");
      point.addFood(food);
    } else {
      log.trace("Point has food already, skipping.");
    }
  }

}
