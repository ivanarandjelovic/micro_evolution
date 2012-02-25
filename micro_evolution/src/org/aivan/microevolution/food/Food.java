package org.aivan.microevolution.food;

import org.apache.log4j.Logger;

/**
 * Base food class.
 * 
 * @author aivan
 * 
 */
public class Food {

  private static final int POWER_VALUE = 10;

  static final Logger log = Logger.getLogger(Food.class);

  private long id;

  public Food(long id) {
    super();
    this.id = id;
  }

  @Override
  public String toString() {
    return this.getClass().getSimpleName() + "/" + id;
  }

  public long getPowerLevel() {
    return POWER_VALUE;
  }

}
