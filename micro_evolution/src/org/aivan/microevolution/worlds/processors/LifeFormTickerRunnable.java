package org.aivan.microevolution.worlds.processors;

import java.util.List;

import org.aivan.microevolution.lifeforms.LifeForm;

public class LifeFormTickerRunnable extends SegmentProcessorRunnable {

  List<LifeForm> lifeForms;

  public LifeFormTickerRunnable(int segmentStart, int segmentEnd, List<LifeForm> lifeForms) {
    super(segmentStart, segmentEnd);
    this.lifeForms = lifeForms;
  }

  @Override
  public void run() {
    for (int i = segmentStart; i < segmentEnd; i++) {
      lifeForms.get(i).tick();
    }

  }
}