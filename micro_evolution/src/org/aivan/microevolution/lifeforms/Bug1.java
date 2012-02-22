package org.aivan.microevolution.lifeforms;

import org.aivan.microevolution.brains.Brain;

public class Bug1 extends LifeForm {

	public Bug1(Brain brain) {
		this.brain = brain;
	}
	
	@Override
	public void tick() {
		brain.tick();
	}

}
