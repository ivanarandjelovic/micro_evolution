package org.aivan.microevolution.brains;

import org.aivan.microevolution.brains.actions.Action;
import org.aivan.microevolution.brains.actions.EatAction;
import org.aivan.microevolution.brains.actions.MoveAction;

/**
 * Not really smart brain, should behave predictable, mostly intended for
 * testing.
 * 
 * @author iarandjelovic
 * 
 */
public class DummyBrain extends Brain {

	private long counter = 0;

	@Override
	public void tick() {
		counter++;
	}

	@Override
	public Action getAction() {
		if (counter % 3 == 0) {
			return new MoveAction();
		} else if (counter % 3 == 1) {
			return new EatAction();
		} else {
			return null;
		}
	}

}
