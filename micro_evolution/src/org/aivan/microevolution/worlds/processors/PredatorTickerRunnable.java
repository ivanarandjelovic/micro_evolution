package org.aivan.microevolution.worlds.processors;

import java.util.List;

import org.aivan.microevolution.lifeforms.predators.Predator;

public class PredatorTickerRunnable extends SegmentProcessorRunnable {

  List<Predator> predators;

  public PredatorTickerRunnable(int segmentStart, int segmentEnd, List<Predator> predators) {
    super(segmentStart,segmentEnd);
    this.predators = predators;
  }

  @Override
  public void run() {
    for (int i = segmentStart; i < segmentEnd; i++) {
      predators.get(i).tick();
    }

  }
}
