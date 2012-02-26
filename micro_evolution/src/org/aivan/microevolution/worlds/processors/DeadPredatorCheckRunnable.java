package org.aivan.microevolution.worlds.processors;

import java.util.List;

import org.aivan.microevolution.lifeforms.predators.Predator;
import org.aivan.microevolution.worlds.points.Point;

public class DeadPredatorCheckRunnable extends SegmentProcessorRunnable {

  List<Point> points;

  public DeadPredatorCheckRunnable(int segmentStart, int segmentEnd, List<Point> points) {
    super(segmentStart,segmentEnd);
    this.points = points;
  }

  @Override
  public void run() {
    for (int i = segmentStart; i < segmentEnd; i++) {
      Point point = points.get(i);
      
      Predator predator = point.getPredator();
      if (predator != null && predator.isDead()) {
        point.setPreadator(null);
      }
      
    }

  }
}
