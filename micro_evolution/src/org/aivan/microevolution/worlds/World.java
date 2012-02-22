package org.aivan.microevolution.worlds;

import java.util.ArrayList;
import java.util.List;

import org.aivan.microevolution.food.Food;
import org.aivan.microevolution.food.FoodFactory;
import org.aivan.microevolution.general.Tickable;
import org.aivan.microevolution.lifeforms.LifeForm;
import org.aivan.microevolution.lifeforms.factories.LifeFormFactory;
import org.aivan.microevolution.worlds.points.Point;

public abstract class World implements Tickable {
	
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
		this.foodFactory = foodFactory;
		this.foodFactory.setWorld(this);
	}

	public List<LifeForm> getLifeForms() {
		return lifeForms;
	}

	@Override
	public void tick() {
		tickCounter++;		foodFactory.tick();
		for(LifeForm lifeForm : lifeForms) {
			lifeForm.tick();
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
		this.lifeFormFactory = lifeFormFactory;
		this.lifeFormFactory.setWorld(this);
	}
	
	
}