package org.aivan.microevolution.lifeforms.predators;

import org.aivan.microevolution.general.Tickable;
import org.aivan.microevolution.lifeforms.LifeForm;

public abstract class Predator implements Tickable {

  public Predator() {
    super();
  }

  public abstract boolean isDead();

  public abstract void meet(LifeForm lifeForm);
}