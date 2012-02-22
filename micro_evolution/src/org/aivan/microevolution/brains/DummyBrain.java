package org.aivan.microevolution.brains;

import org.aivan.microevolution.brains.actions.Action;
import org.aivan.microevolution.brains.actions.EatAction;
import org.aivan.microevolution.brains.actions.MoveAction;
import org.apache.log4j.Logger;

/**
 * Not really smart brain, should behave predictable, mostly intended for
 * testing.
 * 
 * @author iarandjelovic
 * 
 */
public class DummyBrain extends Brain {

	static final Logger log = Logger.getLogger(DummyBrain.class);

	private long counter = 0;

	@Override
	public void tick() {
		log.trace("tick ...");
		counter++;
	}

	@Override
	public Action getAction() {

		Action action = null;

		if (counter % 3 == 0) {
			action = new MoveAction();
		} else if (counter % 3 == 1) {
			action = new EatAction();
		} else {
			action = null;
		}

		log.trace("returning: " + action);

		return action;
	}

}
