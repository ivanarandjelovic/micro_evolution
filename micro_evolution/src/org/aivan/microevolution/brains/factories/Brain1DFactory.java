package org.aivan.microevolution.brains.factories;

import org.aivan.microevolution.brains.Brain;
import org.aivan.microevolution.brains.Brain1D;

public class Brain1DFactory implements BrainFactory {

  @Override
  public Brain createNew() {
    return new Brain1D(this);
  }

  @Override
  public Brain combine(Brain brain1, Brain brain2) {
    // TODO Auto-generated method stub
    return new Brain1D(this);  //TODO: this is only temporary, makes no sense
  }

}
