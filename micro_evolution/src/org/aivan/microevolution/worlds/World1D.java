package org.aivan.microevolution.worlds;

import java.util.ArrayList;

import org.aivan.microevolution.worlds.points.Point;
import org.aivan.microevolution.worlds.points.SimplePoint;
import org.apache.log4j.Logger;

public class World1D extends World {

  static final Logger log = Logger.getLogger(World1D.class);

  private static final long POINT_COUNT = 100;

  @Override
  public void init() {
    log.debug("init in process ...");
    // Create points
    log.debug("Creating points ...");
    points = new ArrayList<Point>();
    Point previousPoint = null;
    for (long i = 0; i < POINT_COUNT; i++) {
      Point point = new SimplePoint(i, this, previousPoint);
      points.add(point);
      previousPoint = point;
    }
    points.get(0).setNext(previousPoint);

    log.debug("Creating food ...");
    // Create food
    foodFactory.init();

    log.debug("Creating life forms ...");
    // Create lifeForms:
    lifeFormFactory.init();

  }

}
