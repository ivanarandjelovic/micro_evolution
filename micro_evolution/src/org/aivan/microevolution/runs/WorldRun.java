package org.aivan.microevolution.runs;

import org.apache.log4j.Logger;

public abstract class WorldRun {

  static final Logger log = Logger.getLogger(WorldRun.class);
  
  protected static final long WORLD_POINT_COUNT = 10000;
  protected static final long TICKS_TO_RUN = 50000;
  protected static final long REPORT_ON_TICKS = 500;
  protected static final long GENERAL_REPORT_ON_TICKS = 0;


}
