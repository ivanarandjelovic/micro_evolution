package org.aivan.microevolution.worlds;

import java.util.ArrayList;

import org.aivan.microevolution.worlds.points.Point;
import org.aivan.microevolution.worlds.points.SimplePoint;
import org.apache.log4j.Logger;

public class World1D extends World {

  static final Logger log = Logger.getLogger(World1D.class);

  private long pointCount;

  public World1D(long pointCount) {
    super();
    this.pointCount = pointCount;
  }



  @Override
  public void init() {
    log.debug("init in process ...");
    // Create points
    log.debug("Creating points ...");
    points = new ArrayList<Point>();
    Point previousPoint = null;
    for (long i = 0; i < pointCount; i++) {
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

    log.debug("Creating predators ...");
    predatorFactory.init();
    
  }

}
