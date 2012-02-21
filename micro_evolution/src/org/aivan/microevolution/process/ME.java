package org.aivan.microevolution.process;

import org.aivan.microevolution.worlds.World;

/**
 * Main class for starting and executing evolutions.
 * 
 * @author iarandjelovic
 * 
 */
public class ME {

	World world = null;
	
	public ME(World world) {
		super();
		this.world = world;
		world.init();
	}



	public void runWorld(long ticks) {
		for(long i = 0 ; i< ticks; i++) {
			world.tick();
		}
	}
	
	public String getReport() {
		String report = "";
		
		report += "World : " + world.getClass().getName();
		report += "\nWorld's tick count: "+world.getTickCounter();
		report += "\nWorld's lifeForm count: "+world.getLifeForms().size();
		report += "\nWorld's food count: "+world.getFood().size();
		
		
		return  report;
	}
}
