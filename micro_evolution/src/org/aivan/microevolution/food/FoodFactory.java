package org.aivan.microevolution.food;

import org.aivan.microevolution.general.Tickable;
import org.aivan.microevolution.worlds.World;

public abstract class FoodFactory implements Tickable {
	
	World world = null;

	public FoodFactory(World world) {
		super();
		this.world = world;
	}
	
	

}
