package org.aivan.microevolution.worlds.processors;

import java.util.List;

import org.aivan.microevolution.lifeforms.LifeForm;
import org.aivan.microevolution.worlds.points.Point;

public class LifeFormTickerRunnable extends SegmentProcessorRunnable {

  private static final int SIGHT_LENGTH_IN_POINTS_FOOD = 6; // 0 - current point
  private static final int SIGHT_LENGTH_IN_POINTS_PREDATOR = 4; // 0 - current
                                                                // point
  private static final int SIGHT_LENGTH_IN_POINTS_LIFE_FORM = 20; // 0 - current
                                                                 // point

  List<LifeForm> lifeForms;

  public LifeFormTickerRunnable(int segmentStart, int segmentEnd, List<LifeForm> lifeForms) {
    super(segmentStart, segmentEnd);
    this.lifeForms = lifeForms;
  }

  @Override
  public void run() {
    for (int i = segmentStart; i < segmentEnd; i++) {
      LifeForm lifeForm = lifeForms.get(i);

      // Set inputs

      Point currentPoint = lifeForm.getLocatonPoint();
      if (currentPoint == null) {
        throw new RuntimeException("lifeForm has no point! " + lifeForm);
      }
      double foodSignal = 0.0;
      double predatorSignal = 0.0;
      double lifeFormSignal = 0.0;
      Point processingPoint = currentPoint;
      for (int pointCounter = 0; pointCounter < SIGHT_LENGTH_IN_POINTS_FOOD; pointCounter++) {
        if (foodSignal == 0 && processingPoint.getFood() != null) {
          foodSignal = (1.0 / SIGHT_LENGTH_IN_POINTS_FOOD) * (SIGHT_LENGTH_IN_POINTS_FOOD - pointCounter);
        }
      }
      for (int pointCounter = 0; pointCounter < SIGHT_LENGTH_IN_POINTS_PREDATOR; pointCounter++) {
        if (predatorSignal == 0 && processingPoint.getPredator() != null) {
          predatorSignal = (1.0 / SIGHT_LENGTH_IN_POINTS_PREDATOR) * (SIGHT_LENGTH_IN_POINTS_PREDATOR - pointCounter);
        }
      }
      for (int pointCounter = 0; pointCounter < SIGHT_LENGTH_IN_POINTS_LIFE_FORM; pointCounter++) {
        if (lifeFormSignal == 0 && processingPoint.getLifeForms().size() > 0) {
          if ((processingPoint != currentPoint) || (processingPoint.getLifeForms().size() > 1)) {
            lifeFormSignal = (1.0 / SIGHT_LENGTH_IN_POINTS_LIFE_FORM) * (SIGHT_LENGTH_IN_POINTS_LIFE_FORM - pointCounter);
          }
        }
        processingPoint = processingPoint.getNext();
      }

      lifeForm.foodInSight(foodSignal);
      lifeForm.predatorInSight(predatorSignal);
      lifeForm.lifeFormInSight(lifeFormSignal);

      // tick (activate decision/brain)
      lifeForm.tick();
    }

  }
}
