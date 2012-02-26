package org.aivan.microevolution.worlds.processors;

import java.util.ArrayList;
import java.util.List;

import org.aivan.microevolution.lifeforms.LifeForm;
import org.aivan.microevolution.worlds.points.Point;
import org.apache.log4j.Logger;

public class ReproductionProcessorRunnable extends SegmentProcessorRunnable {

  static final Logger log = Logger.getLogger(ReproductionProcessorRunnable.class);

  List<Point> points;

  public ReproductionProcessorRunnable(int segmentStart, int segmentEnd, List<Point> points) {
    super(segmentStart, segmentEnd);
    this.points = points;
  }

  @Override
  public void run() {
    for (int i = segmentStart; i < segmentEnd; i++) {
      Point point = points.get(i);
      List<LifeForm> lifeFormsCopy = new ArrayList<LifeForm>(point.getLifeForms());
      for (int lifeFormCount = 0; lifeFormCount < lifeFormsCopy.size() - 1; lifeFormCount++) {
        LifeForm lifeform = lifeFormsCopy.get(lifeFormCount);
        if (lifeform.canReproduce()) {
          if (lifeFormCount < (lifeFormsCopy.size() - 1)) {
            // there more life forms
            LifeForm pairForm = lifeFormsCopy.get(lifeFormCount + 1);
            if (pairForm.canReproduce()) {
              // Do re-production
              LifeForm newLifeForm = lifeform.reproduceWith(pairForm);
              point.lifeFormEntered(newLifeForm);

              log.trace("Reproduction done, new life form: " + newLifeForm);

              // skip pair, he/she cannot reproduce immediately
              lifeFormCount++;
            }
          }

        }
      }
    }
    // TODO Auto-generated method stub

  }

}
