package org.aivan.microevolution.lifeforms;

import java.util.List;

import org.aivan.microevolution.brains.Brain;
import org.aivan.microevolution.brains.actions.Action;
import org.aivan.microevolution.general.Tickable;
import org.apache.log4j.Logger;

public abstract class LifeForm implements Tickable {

	static final Logger log = Logger.getLogger(LifeForm.class);

	protected Brain brain = null;
	
	public abstract List<Action> getActions();

	private long id;

	public LifeForm(long id, Brain brain) {
		super();
		this.brain = brain;
		this.id = id;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.getClass().getSimpleName()+"/"+id;
	}
	
	
}
