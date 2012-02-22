package org.aivan.microevolution.lifeforms;

import org.aivan.microevolution.brains.Brain;
import org.aivan.microevolution.general.Tickable;
import org.apache.log4j.Logger;

public abstract class LifeForm implements Tickable {

	static final Logger log = Logger.getLogger(LifeForm.class);

	protected Brain brain = null;

}
