package org.aivan.microevolution.worlds.processors;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.aivan.microevolution.lifeforms.LifeForm;
import org.aivan.microevolution.worlds.World;
import org.aivan.microevolution.worlds.points.Point;
import org.apache.log4j.Logger;

public class DeadLifeFormProcessorRunnable extends SegmentProcessorRunnable {

  static final Logger log = Logger.getLogger(ActionProcessorRunnable.class);

  List<Point> points;
  World world;

  public DeadLifeFormProcessorRunnable(int segmentStart, int segmentEnd, List<Point> points, World world) {
    super(segmentStart, segmentEnd);
    this.points = points;
    this.world = world;
  }

  @Override
  public void run() {
    for (int i = segmentStart; i < segmentEnd; i++) {
      Point point = points.get(i);

      Set<LifeForm> lifeFormsCopy = new HashSet<LifeForm>(point.getLifeForms());
      for (LifeForm lifeForm : lifeFormsCopy) {
        if (lifeForm.isDead()) {
          point.lifeFormLeft(lifeForm);
          world.getLifeForms().remove(lifeForm);
          world.increaseDeadLifeFormCount(lifeForm.getDiedFrom());
        }
      }
    }
  }

}
