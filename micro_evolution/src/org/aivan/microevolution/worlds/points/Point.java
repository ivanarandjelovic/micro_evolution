package org.aivan.microevolution.worlds.points;

import java.util.Set;

import org.aivan.microevolution.food.Food;
import org.aivan.microevolution.lifeforms.LifeForm;
import org.aivan.microevolution.worlds.World;

/**
 * Abstract class for world points. They belong to a world, and have a set of
 * life forms on them. Also, there can be food on it.
 * 
 * @author aivan
 * 
 */
public abstract class Point {

	World world = null;

	Set<LifeForm> lifeForms = null;

	Food food = null;

	Point(World world) {
		super();
		this.world = world;
	}

	void lifeFormEntered(LifeForm lifeForm) {
		lifeForms.add(lifeForm);
	}

	void lifeFormLeft(LifeForm lifeForm) {
		lifeForms.remove(lifeForm);
	}

	public Food getFood() {
		return food;
	}

	public void addFood(Food food) {
		if (this.food == null) {
			this.food = food;
			world.getFood().add(food);
		} else {
			throw new RuntimeException("Food already exists on this point!");
		}
	}

}
