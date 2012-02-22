package org.aivan.microevolution.process;

import org.aivan.microevolution.worlds.World;
import org.apache.log4j.Logger;

/**
 * Main class for starting and executing evolutions.
 * 
 * @author iarandjelovic
 * 
 */
public class ME {

	static final Logger log = Logger.getLogger(ME.class);

	World world = null;

	public ME(World world) {
		super();
		log.debug("MicroEvolution created, world: " + world);
		this.world = world;
		log.debug("Initializing world ...");
		world.init();
	}

	public void runWorld(long ticks) {
		log.debug("Ticking world for: " + ticks + " ticks ...");
		for (long i = 0; i < ticks; i++) {
			log.debug("Tick: " + i + " ticking ...");
			world.tick();
		}
	}

	public String getReport() {
		String report = "";
		
		report += "World : " + world.getClass().getName();
		report += "\nWorld's tick count: "+world.getTickCounter();
		report += "\nWorld's lifeForm count: "+world.getLifeForms().size();
		report += "\nWorld's food count: "+world.getFood().size();
		report += "\nWorld's lifeForm placement:"+world.getLifeFormReport();
		report += "\nWorld's food placement:"+world.getFoodReport();
		report += "\nWorld's life forms and food placement:"+world.getLifeFormAndFoodReport();
		
		return  report;
	}
}
