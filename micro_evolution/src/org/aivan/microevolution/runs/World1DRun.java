package org.aivan.microevolution.runs;

import org.aivan.microevolution.food.ConstantFoodFactory;
import org.aivan.microevolution.lifeforms.factories.SimpleLifeFormFactory;
import org.aivan.microevolution.lifeforms.predators.ConstantPredatorFactory;
import org.aivan.microevolution.process.ME;
import org.aivan.microevolution.worlds.World;
import org.aivan.microevolution.worlds.World1D;
import org.apache.log4j.Logger;

public class World1DRun {

  static final Logger log = Logger.getLogger(World1DRun.class);

  private static final long WORLD_POINT_COUNT = 10000;
  private static final long TICKS_TO_RUN = 10;
  private static final long REPORT_ON_TICKS = 500;
  private static final long GENERAL_REPORT_ON_TICKS = 100;
  

  /**
   * @param args
   */
  public static void main(String[] args) {

    log.info("Starting run...");

    long startTime = System.currentTimeMillis();

    World world = new World1D(WORLD_POINT_COUNT);
    world.setFoodFactory(new ConstantFoodFactory());
    world.setLifeFormFactory(new SimpleLifeFormFactory());
    world.setPredatorFactory(new ConstantPredatorFactory(world));
    ME me = new ME(world);

    me.runWorld(TICKS_TO_RUN, REPORT_ON_TICKS, GENERAL_REPORT_ON_TICKS);

    log.info("Run complete.");

    long endTime = System.currentTimeMillis();

    log.info("Run took: " + (endTime - startTime) / 1000.0 + " seconds");
  }

}
