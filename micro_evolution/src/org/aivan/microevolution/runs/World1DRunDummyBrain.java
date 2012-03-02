package org.aivan.microevolution.runs;

import org.aivan.microevolution.brains.dummy.DummyBrainFactory;
import org.apache.log4j.Logger;

public class World1DRunDummyBrain extends WorldRun {

  static final Logger log = Logger.getLogger(World1DRunDummyBrain.class);
 

  public World1DRunDummyBrain() {
    super();
    worldRunConfiguration.setBrainFactory(new DummyBrainFactory());
  }


  /**
   * @param args
   */
  public static void main(String[] args) {
    World1DRunDummyBrain worldRun = new World1DRunDummyBrain();
    WorldRunner.run(worldRun);
  }

}
