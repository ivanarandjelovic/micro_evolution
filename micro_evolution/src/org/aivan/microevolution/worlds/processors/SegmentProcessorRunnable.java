package org.aivan.microevolution.worlds.processors;

public abstract class SegmentProcessorRunnable implements Runnable {

  protected int segmentStart;
  protected int segmentEnd;

  public SegmentProcessorRunnable(int segmentStart, int segmentEnd) {
    super();
    this.segmentStart = segmentStart;
    this.segmentEnd = segmentEnd;
  }

}