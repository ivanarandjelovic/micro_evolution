package org.aivan.microevolution.brains;

import org.aivan.microevolution.brains.actions.Action;
import org.aivan.microevolution.general.Tickable;

public abstract class Brain implements Tickable {

	public abstract Action getAction();
	
}
