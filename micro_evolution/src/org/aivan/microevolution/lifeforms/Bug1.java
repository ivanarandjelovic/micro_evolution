package org.aivan.microevolution.lifeforms;

import java.util.List;

import org.aivan.microevolution.brains.Brain;
import org.aivan.microevolution.brains.actions.Action;
import org.apache.log4j.Logger;

public class Bug1 extends LifeForm {

	static final Logger log = Logger.getLogger(Bug1.class);

	public Bug1(Brain brain) {
		this.brain = brain;
	}

	@Override
	public void tick() {
		log.trace("tick...");
		
		log.trace("ticking brain ...");
		brain.tick();
	}

	@Override
	public List<Action> getActions() {
		return brain.getActions();
	}

}
