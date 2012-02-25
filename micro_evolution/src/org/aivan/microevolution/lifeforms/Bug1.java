package org.aivan.microevolution.lifeforms;


import org.aivan.microevolution.brains.Brain;
import org.apache.log4j.Logger;

public class Bug1 extends LifeForm {

  public Bug1(long id, Brain brain, long initialPowerLevel) {
    super(id, brain, initialPowerLevel);
  }

  static final Logger log = Logger.getLogger(Bug1.class);

}
