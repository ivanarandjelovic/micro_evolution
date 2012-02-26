package org.aivan.microevolution.lifeforms.factories;

import org.aivan.microevolution.worlds.World;
import org.apache.log4j.Logger;

public abstract class LifeFormFactory {

  static final Logger log = Logger.getLogger(LifeFormFactory.class);

  private static long lifeFormCounter = 0;

  World world = null;

  public void setWorld(World world) {
    //log.trace("World set: " + world);
    this.world = world;
  }

  public abstract void init();

  synchronized protected long getNextLifeFormCounter() {
    return lifeFormCounter++;
  }
}
