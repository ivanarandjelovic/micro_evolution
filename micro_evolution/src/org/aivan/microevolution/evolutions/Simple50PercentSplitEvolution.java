package org.aivan.microevolution.evolutions;

import org.aivan.microevolution.general.Tickable;
import org.aivan.microevolution.worlds.World;
import org.apache.log4j.Logger;

public class Simple50PercentSplitEvolution implements Tickable {

  static final Logger log = Logger.getLogger(Simple50PercentSplitEvolution.class);

  World world = null;
  
  
  public Simple50PercentSplitEvolution(World world) {
    super();
    this.world = world;
  }


  @Override
  public void tick() {
    // TODO Auto-generated method stub
    
  }

}
