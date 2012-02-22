package org.aivan.microevolution.worlds.points;

import java.util.HashSet;
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

	Set<LifeForm> lifeForms =  new HashSet<LifeForm>();;

	Food food = null;

	Point(World world) {
		super();
		this.world = world;
	}

	public void lifeFormEntered(LifeForm lifeForm) {
		lifeForms.add(lifeForm);
	}

	public void lifeFormLeft(LifeForm lifeForm) {
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

	public Food removeFood() {
		Food eatenFood = this.food;
		if (this.food != null) {
			world.getFood().remove(this.food);
			this.food = null;
		} else {
			throw new RuntimeException("Food does not exists here!");
		}
		return eatenFood;
	}

}
