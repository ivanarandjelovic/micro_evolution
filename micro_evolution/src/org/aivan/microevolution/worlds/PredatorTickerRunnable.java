package org.aivan.microevolution.worlds;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.aivan.microevolution.lifeforms.predators.Predator;

public class PredatorTickerRunnable implements Runnable {

  int segmentStart;
  int segmentEnd;
  List<Predator> predators;

  public PredatorTickerRunnable(int segmentStart, int segmentEnd, List<Predator> predators) {
    super();
    this.segmentStart = segmentStart;
    this.segmentEnd = segmentEnd;
    this.predators = predators;
  }

  @Override
  public void run() {
    for (int i = segmentStart; i < segmentEnd; i++) {
      predators.get(i).tick();
    }

  }
}
