package org.aivan.microevolution.runs;

import org.aivan.microevolution.brains.brain1d.Brain1DFactory;
import org.apache.log4j.Logger;

public class World1DRunBrain1D  extends WorldRun {

  static final Logger log = Logger.getLogger(World1DRunBrain1D.class);

  
  public World1DRunBrain1D() {
    super();
    worldRunConfiguration.setBrainFactory(new Brain1DFactory());
  }


  /**
   * @param args
   */
  public static void main(String[] args) {
    World1DRunBrain1D worldRun = new World1DRunBrain1D();
    WorldRunner.run(worldRun);
  }

}
