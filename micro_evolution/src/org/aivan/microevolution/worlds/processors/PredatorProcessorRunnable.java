package org.aivan.microevolution.worlds.processors;

import java.util.List;

import org.aivan.microevolution.lifeforms.predators.Predator;
import org.aivan.microevolution.worlds.points.Point;

public class PredatorProcessorRunnable extends SegmentProcessorRunnable {

  List<Point> points;

  public PredatorProcessorRunnable(int segmentStart, int segmentEnd, List<Point> points) {
    super(segmentStart, segmentEnd);
    this.points = points;
  }

  @Override
  public void run() {
    for (int i = segmentStart; i < segmentEnd; i++) {
      Point point = points.get(i);

      if (point.getPredator() != null && point.getLifeForms().size() > 0) {
        Predator predator = point.getPredator();
        predator.meet(point.getLifeForms().get(0));
      }

    }

  }
}
