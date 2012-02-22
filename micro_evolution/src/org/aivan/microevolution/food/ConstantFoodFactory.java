package org.aivan.microevolution.food;

import java.util.List;

import org.aivan.microevolution.worlds.points.Point;

public class ConstantFoodFactory extends FoodFactory {

	public static final double INITIAL_FOOD_COUNT_PERCENT = 5.0;
	public static final double FOOD_PER_POINT_PER_TICK = 0.05;
	public static final double MAX_POINT_WITH_FOOD_PERCENT = 25;

	private double currentFoodFactor = 0.0;

	public ConstantFoodFactory() {
	}

	@Override
	public void tick() {
		List<Point> points = world.getPoints();
		long pointCount = points.size();

		currentFoodFactor += (pointCount * FOOD_PER_POINT_PER_TICK);

		if (currentFoodFactor >= 1.0) {
			while (currentFoodFactor >= 1.0) {
				if (pointCount / 100.0 * world.getFood().size() < MAX_POINT_WITH_FOOD_PERCENT) {
					generateOneFood(points, pointCount);
				}
				currentFoodFactor -= 1.0;
			}
		}
	}

	@Override
	public void init() {
		List<Point> points = world.getPoints();
		long pointCount = points.size();

		while (pointCount / 100.0 * world.getFood().size() < INITIAL_FOOD_COUNT_PERCENT) {
			generateOneFood(points, pointCount);
		}

	}

	private void generateOneFood(List<Point> points, long pointCount) {
		Food food = new Food();
		int rand = (int) Math.floor(Math.random() * pointCount);
		Point point = points.get(rand);
		if (point.getFood() == null) {
			point.addFood(food);
		}
	}

}
