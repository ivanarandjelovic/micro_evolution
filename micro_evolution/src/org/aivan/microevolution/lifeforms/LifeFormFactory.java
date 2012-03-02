package org.aivan.microevolution.lifeforms;

import org.aivan.microevolution.brains.BrainFactory;
import org.aivan.microevolution.worlds.World;
import org.apache.log4j.Logger;

public abstract class LifeFormFactory {

  static final Logger log = Logger.getLogger(LifeFormFactory.class);

  private static long lifeFormCounter = 0;

  World world = null;

  protected BrainFactory brainFactory;

  public void setWorld(World world) {
    //log.trace("World set: " + world);
    this.world = world;
  }

  public abstract void init();

  synchronized public long getNextLifeFormCounter() {
    return lifeFormCounter++;
  }

  public void setBrainFactory(BrainFactory brainFactory) {
    this.brainFactory = brainFactory;
  }
}
