package org.aivan.microevolution.runs;

import org.aivan.microevolution.food.ConstantFoodFactory;
import org.aivan.microevolution.lifeforms.factories.SimpleLifeFormFactory;
import org.aivan.microevolution.process.ME;
import org.aivan.microevolution.worlds.World;
import org.aivan.microevolution.worlds.World1D;

public class World1DRun {

	private static final long TICKS_TO_RUN_REPORT = 10;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		World world = new World1D();
		world.setFoodFactory(new ConstantFoodFactory());
		world.setLifeFormFactory(new SimpleLifeFormFactory());
		ME me = new ME(world);

		System.out.println("\nReport at START: ");
		System.out.println("================ ");
		System.out.println(me.getReport());

		me.runWorld(TICKS_TO_RUN_REPORT);

		System.out.println("\nReport at END: ");
		System.out.println("================ ");
		System.out.println(me.getReport());
	}

}
