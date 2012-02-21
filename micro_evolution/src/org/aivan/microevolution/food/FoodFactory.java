package org.aivan.microevolution.food;

import org.aivan.microevolution.general.Tickable;
import org.aivan.microevolution.worlds.World;

public abstract class FoodFactory implements Tickable {
	
	World world = null;

	public FoodFactory() {
		super();
	}
	
	public abstract void init();

	public void setWorld(World world) {
		this.world = world;
	}

	
}
