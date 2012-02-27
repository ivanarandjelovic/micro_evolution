package org.aivan.microevolution.runs;

import org.aivan.microevolution.brains.factories.DummyBrainFactory;
import org.aivan.microevolution.food.ConstantFoodFactory;
import org.aivan.microevolution.lifeforms.factories.SimpleLifeFormFactory;
import org.aivan.microevolution.lifeforms.predators.ConstantPredatorFactory;
import org.aivan.microevolution.process.ME;
import org.aivan.microevolution.worlds.World;
import org.aivan.microevolution.worlds.World1D;
import org.apache.log4j.Logger;

public class World1DRunDummyBrain extends WorldRun {

  static final Logger log = Logger.getLogger(World1DRunDummyBrain.class);
 

  /**
   * @param args
   */
  public static void main(String[] args) {

    log.info("Starting run of: "+World1DRunDummyBrain.class.getSimpleName());

    long startTime = System.currentTimeMillis();

    World world = new World1D(WORLD_POINT_COUNT);
    world.setFoodFactory(new ConstantFoodFactory());
    world.setLifeFormFactory(new SimpleLifeFormFactory(new DummyBrainFactory()));
    world.setPredatorFactory(new ConstantPredatorFactory(world));
    ME me = new ME(world);

    try {
      me.runWorld(TICKS_TO_RUN, REPORT_ON_TICKS, GENERAL_REPORT_ON_TICKS);
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    log.info("Run complete.");

    long endTime = System.currentTimeMillis();

    log.info("Run took: " + (endTime - startTime) / 1000.0 + " seconds");
  }

}
