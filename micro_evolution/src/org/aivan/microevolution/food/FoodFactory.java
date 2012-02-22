package org.aivan.microevolution.food;

import org.aivan.microevolution.general.Tickable;
import org.aivan.microevolution.worlds.World;
import org.apache.log4j.Logger;

public abstract class FoodFactory implements Tickable {

	static final Logger log = Logger.getLogger(FoodFactory.class);

	World world = null;

	public FoodFactory() {
		super();
		log.trace("Created");
	}

	public abstract void init();

	public void setWorld(World world) {
		log.trace("world is set");
		this.world = world;
	}

}
