package org.aivan.microevolution.brains.actions;

import org.apache.log4j.Logger;

public abstract class Action {

  static final Logger log = Logger.getLogger(Action.class);

  @Override
  public String toString() {
    return this.getClass().getSimpleName();
  }
}
