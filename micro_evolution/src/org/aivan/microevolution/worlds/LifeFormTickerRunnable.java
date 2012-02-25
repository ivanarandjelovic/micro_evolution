package org.aivan.microevolution.worlds;

import java.util.List;

import org.aivan.microevolution.lifeforms.LifeForm;

public class LifeFormTickerRunnable implements Runnable {

  int segmentStart;
  int segmentEnd;
  List<LifeForm> lifeForms;

  public LifeFormTickerRunnable(int segmentStart, int segmentEnd, List<LifeForm> lifeForms) {
    super();
    this.segmentStart = segmentStart;
    this.segmentEnd = segmentEnd;
    this.lifeForms = lifeForms;
  }

  @Override
  public void run() {
    for(int i = segmentStart; i<segmentEnd; i++) {
      lifeForms.get(i).tick();
    }

  }
}
