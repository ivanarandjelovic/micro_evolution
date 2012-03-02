package org.aivan.microevolution.predators;

import org.aivan.microevolution.general.Tickable;
import org.aivan.microevolution.lifeforms.LifeForm;
import org.apache.log4j.Logger;

public class Predator1 extends Predator implements Tickable {

  static final Logger log = Logger.getLogger(Predator1.class);

  long duration;

  public Predator1(long id, long duration) {
    super(id);
    this.duration = duration;
    //log.trace(this + " created with duration: " + duration);
  }

  @Override
  public void tick() {
    //log.trace("tick ... on " + this);
    if (duration > 0) {
      duration--;
    } else {
      throw new RuntimeException("Duration of the predator is already 0!!! for: "+this);
    }
  }

  public void meet(LifeForm lifeForm) {
    lifeForm.kill();
  }

  public boolean isDead() {
    return duration == 0;
  }

  @Override
  public String toString() {
    return this.getClass().getSimpleName() + "/" + id;
  }
}
