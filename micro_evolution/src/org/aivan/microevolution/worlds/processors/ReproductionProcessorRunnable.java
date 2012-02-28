package org.aivan.microevolution.worlds.processors;

import java.util.ArrayList;
import java.util.List;

import org.aivan.microevolution.lifeforms.LifeForm;
import org.aivan.microevolution.worlds.World;
import org.aivan.microevolution.worlds.points.Point;
import org.apache.log4j.Logger;

public class ReproductionProcessorRunnable extends SegmentProcessorRunnable {

  static final Logger log = Logger.getLogger(ReproductionProcessorRunnable.class);

  public static final long MAX_REPRODUCTION_DISTANCE_IN_POINTS = 5;
  List<Point> points;
  World world;

  public ReproductionProcessorRunnable(int segmentStart, int segmentEnd, List<Point> points, World world) {
    super(segmentStart, segmentEnd);
    this.points = points;
    this.world = world;
  }

  @Override
  public void run() {
    for (int i = segmentStart; i < segmentEnd; i++) {
      Point point = points.get(i);
      List<LifeForm> lifeFormsCopy = new ArrayList<LifeForm>(point.getLifeForms());
      for (int lifeFormCount = 0; lifeFormCount < lifeFormsCopy.size(); lifeFormCount++) {
        LifeForm lifeform = lifeFormsCopy.get(lifeFormCount);
        if (lifeform.canReproduce()) {
          boolean reproductionDone = false;
          long distanceCounter = 0;
          Point distantPoint = point;
          while (!reproductionDone && distanceCounter <= MAX_REPRODUCTION_DISTANCE_IN_POINTS) {
            reproductionDone = tryReproduction(lifeform, point, distantPoint);
            distantPoint = distantPoint.getNext();
          }
        }
      }
    }
  }

  // If there is suitable lifeform on "distantPoint" then reproduce them and
  // place child on "point"
  private boolean tryReproduction(LifeForm lifeform, Point point, Point distantPoint) {
    boolean reproductionDone = false;
    for (LifeForm pairForm : distantPoint.getLifeForms()) {
      if (pairForm.canReproduce()) {
        // Do re-production
        LifeForm newLifeForm = lifeform.reproduceWith(pairForm);

        // Introduce life form into the world
        point.lifeFormEntered(newLifeForm);
        world.getLifeForms().add(newLifeForm);

        log.trace("Reproduction done on point: " + point + ", new life form: " + newLifeForm);
        reproductionDone = true;
        break;
      }
    }
    return reproductionDone;
  }

}
