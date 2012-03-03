package org.aivan.microevolution.runs;

import org.aivan.microevolution.process.ME;
import org.aivan.microevolution.worlds.World;
import org.aivan.microevolution.worlds.World1D;
import org.apache.commons.math.stat.descriptive.SummaryStatistics;
import org.apache.log4j.Logger;

public abstract class WorldRunner {

  static final Logger log = Logger.getLogger(WorldRunner.class);

  public static void run(WorldRun worldRun) {

    SummaryStatistics tickStat = new SummaryStatistics();
    SummaryStatistics timeStat = new SummaryStatistics();

    WorlRunConfiguration configuration = worldRun.getWorldRunConfiguration();

    log.info("Starting run of: " + worldRun.getClass().getSimpleName() + " which will be repeated " + configuration.getRepeatRuns()
        + " times");

    for (long i = 0; i < configuration.getRepeatRuns(); i++) {

      long startTime = System.currentTimeMillis();

      World world = new World1D(configuration.getWorldPointCount());
      world.setFoodFactory(configuration.getFoodFactory());
      configuration.getLifeFormFactory().setBrainFactory(configuration.getBrainFactory());
      world.setLifeFormFactory(configuration.getLifeFormFactory());

      configuration.getPredatorFactory().setWorld(world);
      world.setPredatorFactory(configuration.getPredatorFactory());
      ME me = new ME(world);

      try {
        me.runWorld(configuration.getTicksToRun(), configuration.getReportOnTicks(), configuration.getBrainReportOnTicks());
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

      log.info("Run complete.");

      long endTime = System.currentTimeMillis();

      log.info("Run took: " + (endTime - startTime) / 1000.0 + " seconds");

      timeStat.addValue(endTime - startTime);
      tickStat.addValue(world.getTickCounter());
    }

    log.info("all runs completed, mean time: " + (timeStat.getMean()) + " mean tick count: " + tickStat.getMean());
  }

}
