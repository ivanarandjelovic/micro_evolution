package org.aivan.microevolution.lifeforms.predators;

import org.aivan.microevolution.general.Tickable;
import org.aivan.microevolution.worlds.World;
import org.apache.log4j.Logger;

public abstract class PredatorFactory implements Tickable {

  static final Logger log = Logger.getLogger(PredatorFactory.class);
  
  World world = null;
  
  private static long predatorCounter = 0;
  
  public PredatorFactory(World world) {
    super();
    this.world = world;
  }

  public abstract void init();
  
  synchronized protected long getNextPredatorCounter() {
    return predatorCounter++;
  }
}