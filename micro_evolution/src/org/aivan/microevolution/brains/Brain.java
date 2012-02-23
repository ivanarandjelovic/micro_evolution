package org.aivan.microevolution.brains;

import java.util.List;

import org.aivan.microevolution.brains.actions.Action;
import org.aivan.microevolution.general.Tickable;
import org.apache.log4j.Logger;

public abstract class Brain implements Tickable {

	static final Logger log = Logger.getLogger(Brain.class);

	public abstract List<Action> getActions();
	
}
