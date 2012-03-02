package org.aivan.microevolution.brains.dead;

import org.aivan.microevolution.brains.Brain;
import org.aivan.microevolution.brains.BrainFactory;

public class BrainDeadFactory implements BrainFactory {

  @Override
  public Brain createNew() {
    return new BrainDead(this);
  }

  @Override
  public Brain combine(Brain brain1, Brain brain2) {
    return new BrainDead(this);
  }

}
