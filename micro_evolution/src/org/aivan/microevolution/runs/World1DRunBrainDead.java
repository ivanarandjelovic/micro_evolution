package org.aivan.microevolution.runs;

import org.aivan.microevolution.brains.dead.BrainDeadFactory;
import org.apache.log4j.Logger;

public class World1DRunBrainDead  extends WorldRun {

  static final Logger log = Logger.getLogger(World1DRunBrainDead.class);

  
  public World1DRunBrainDead() {
    super();
    worldRunConfiguration.setBrainFactory(new BrainDeadFactory());
  }


  /**
   * @param args
   */
  public static void main(String[] args) {
    World1DRunBrainDead worldRun = new World1DRunBrainDead();
    WorldRunner.run(worldRun);
  }
}
