package org.aivan.microevolution.predators;

import org.aivan.microevolution.general.Tickable;
import org.aivan.microevolution.lifeforms.LifeForm;

public abstract class Predator implements Tickable {

  long id;
  
  public Predator(long id) {
    super();
    this.id = id;
  }

  public abstract boolean isDead();

  public abstract void meet(LifeForm lifeForm);
}