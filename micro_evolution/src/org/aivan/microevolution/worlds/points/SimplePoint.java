package org.aivan.microevolution.worlds.points;

import org.aivan.microevolution.worlds.World;
import org.apache.log4j.Logger;

/**
 * Simple points has no special features. Just basic stuff inherited from the
 * base class.
 * 
 * @author aivan
 * 
 */
public class SimplePoint extends Point {

  static final Logger log = Logger.getLogger(SimplePoint.class);

  public SimplePoint(World world) {
    super(world);
  }

}
