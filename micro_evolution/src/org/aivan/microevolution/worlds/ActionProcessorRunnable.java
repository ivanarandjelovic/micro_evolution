package org.aivan.microevolution.worlds;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.aivan.microevolution.brains.actions.Action;
import org.aivan.microevolution.brains.actions.EatAction;
import org.aivan.microevolution.brains.actions.MoveAction;
import org.aivan.microevolution.lifeforms.LifeForm;
import org.aivan.microevolution.worlds.points.Point;
import org.apache.log4j.Logger;

public class ActionProcessorRunnable extends SegmentRunnable {

  static final Logger log = Logger.getLogger(ActionProcessorRunnable.class);

  List<Point> points;

  public ActionProcessorRunnable(int segmentStart, int segmentEnd, List<Point> points) {
    super(segmentStart, segmentEnd);
    this.points = points;
  }

  @Override
  public void run() {
    for (int i = segmentStart; i < segmentEnd; i++) {
      Point point = points.get(i);

      //log.trace("processing point: " + point);
      // We need a copy of a set due to concurrent updated while iterating
      Set<LifeForm> lifeFormsCopy = new HashSet<LifeForm>(point.getLifeForms());
      for (LifeForm lifeForm : lifeFormsCopy) {
        List<Action> actions = lifeForm.getActions();
        if (actions.isEmpty()) {
          //log.trace("lifeform: " + lifeForm + " no actions.");
        } else {
          for (Action action : actions) {
            if (action instanceof EatAction) {
              EatAction eatAction = (EatAction) action;
              //log.trace("lifeform: " + lifeForm + " eating: " + eatAction);
              if (point.getFood() != null) {
                lifeForm.eat(point.getFood());
                point.setFood(null);
              }
            } else if (action instanceof MoveAction) {
              MoveAction moveAction = (MoveAction) action;
              //log.trace("lifeform: " + lifeForm + " moving: " + moveAction);
              point.lifeFormLeft(lifeForm);
              point.getNext().lifeFormEntered(lifeForm);
              lifeForm.moved();
            } else {
              throw new RuntimeException("Unknown action type!?: " + action);
            }
          }
        }
      }
    }
  }

}
