package org.aivan.microevolution.brains;

import java.util.ArrayList;
import java.util.List;

import org.aivan.microevolution.brains.actions.Action;
import org.aivan.microevolution.brains.actions.EatAction;
import org.aivan.microevolution.brains.actions.MoveAction;
import org.aivan.microevolution.brains.factories.BrainFactory;
import org.apache.log4j.Logger;

/**
 * Not really smart brain, should behave predictable, mostly intended for
 * testing. Ignores any input, produces random output:
 * 
 * 33% eating, 33% moving, 33% doing nothing
 * 
 * @author iarandjelovic
 * 
 */
public class DummyBrain extends Brain {


  static final Logger log = Logger.getLogger(DummyBrain.class);


  @SuppressWarnings("unused")
  private long tickCounter = 0;

  
  public DummyBrain(BrainFactory brainFactory) {
    super(brainFactory);
  }

  @Override
  public void tick() {
    tickCounter++;
    //log.trace("tick ... (counter: " + tickCounter + ")");
  }

  @Override
  public List<Action> getActions() {
    ArrayList<Action> actions = new ArrayList<Action>();

    Action action = null;

    int rand = (int) Math.floor(Math.random() * 3);

    if (rand % 3 == 0) {
      action = new MoveAction();
    } else if (rand % 3 == 1) {
      action = new EatAction();
    } else {
      action = null;
    }

    //log.trace("returning: " + action);

    if (action != null) {
      actions.add(action);
    }

    return actions;
  }

  @Override
  public void foodInSight(double signalStrength) {
    // dummy brain ignores inputs
  }

  @Override
  public void predatorInSight(double signalStrength) {
    // dummy brain ignores inputs
  }

  @Override
  public void lifeFormInSight(double signalStrength) {
    // dummy brain ignores inputs
  }

  @Override
  public void hunger(double signalStrength) {
    // dummy brain ignores inputs
  }

}
