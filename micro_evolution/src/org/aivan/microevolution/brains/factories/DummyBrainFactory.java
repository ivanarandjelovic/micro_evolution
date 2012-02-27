package org.aivan.microevolution.brains.factories;

import org.aivan.microevolution.brains.Brain;
import org.aivan.microevolution.brains.DummyBrain;

public class DummyBrainFactory implements BrainFactory {

  @Override
  public Brain createNew() {
    return new DummyBrain(this);
  }

  @Override
  public Brain combine(Brain brain1, Brain brain2) {
    // Dummy brains are always the same
    return new DummyBrain(this);
  }

}
