package org.aivan.microevolution.runs;

import org.aivan.microevolution.food.ConstantFoodFactory;
import org.aivan.microevolution.lifeforms.factories.SimpleLifeFormFactory;
import org.aivan.microevolution.process.ME;
import org.aivan.microevolution.worlds.World;
import org.aivan.microevolution.worlds.World1D;
import org.apache.log4j.Logger;

public class World1DRun {

	static final Logger log = Logger.getLogger(World1DRun.class);
	
	private static final long TICKS_TO_RUN_REPORT = 10;

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		log.info("Starting run...");
	
		World world = new World1D();
		world.setFoodFactory(new ConstantFoodFactory());
		world.setLifeFormFactory(new SimpleLifeFormFactory());
		ME me = new ME(world);

		log.info("Report at START: ");
		log.info("================ ");
		log.info(me.getReport());

		me.runWorld(TICKS_TO_RUN_REPORT);

		log.info("Report at END: ");
		log.info("================ ");
		log.info(me.getReport());

		log.info("Run complete.");
}

}
