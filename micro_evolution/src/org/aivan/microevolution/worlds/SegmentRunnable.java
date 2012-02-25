package org.aivan.microevolution.worlds;

public abstract class SegmentRunnable implements Runnable {

  protected int segmentStart;
  protected int segmentEnd;

  public SegmentRunnable(int segmentStart, int segmentEnd) {
    super();
    this.segmentStart = segmentStart;
    this.segmentEnd = segmentEnd;
  }

}