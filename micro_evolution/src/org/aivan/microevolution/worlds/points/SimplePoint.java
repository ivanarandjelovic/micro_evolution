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

  public SimplePoint(long id, World world, Point next) {
    super(id, world, next);
  }



  static final Logger log = Logger.getLogger(SimplePoint.class);

}
