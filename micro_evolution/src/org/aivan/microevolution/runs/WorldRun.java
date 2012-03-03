package org.aivan.microevolution.runs;

import org.aivan.microevolution.food.ConstantFoodFactory;
import org.aivan.microevolution.lifeforms.SimpleLifeFormFactory;
import org.aivan.microevolution.predators.ConstantPredatorFactory;
import org.apache.log4j.Logger;

public abstract class WorldRun {

  static final Logger log = Logger.getLogger(WorldRun.class);
  
  protected static final long WORLD_POINT_COUNT = 10000;
  protected static final long TICKS_TO_RUN = 500000;
  protected static final long REPORT_ON_TICKS = 500;
  protected static final long REPEAT_RUNS = 1;
  protected static final long BRAIN_REPORT_ON_TICKS = 0;
  
  
  protected WorlRunConfiguration worldRunConfiguration = new WorlRunConfiguration();

  
  public WorlRunConfiguration getWorldRunConfiguration() {
    return worldRunConfiguration;
  }


  public WorldRun() {
    super();
    worldRunConfiguration.setWorldPointCount(WORLD_POINT_COUNT);
    worldRunConfiguration.setTicksToRun(TICKS_TO_RUN);
    worldRunConfiguration.setReportOnTicks(REPORT_ON_TICKS);
    worldRunConfiguration.setFoodFactory(new ConstantFoodFactory());
    worldRunConfiguration.setPredatorFactory(new ConstantPredatorFactory(null));
    worldRunConfiguration.setLifeFormFactory(new SimpleLifeFormFactory(null));
    worldRunConfiguration.setRepeatRuns(REPEAT_RUNS);
    worldRunConfiguration.setBrainReportOnTicks(BRAIN_REPORT_ON_TICKS);
  }

  
}
