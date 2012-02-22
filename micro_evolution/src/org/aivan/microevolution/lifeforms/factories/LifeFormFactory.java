package org.aivan.microevolution.lifeforms.factories;

import org.aivan.microevolution.worlds.World;

public abstract class LifeFormFactory {
	World world = null;

	public void setWorld(World world) {
		this.world = world;
	}

	public abstract void init();
}
