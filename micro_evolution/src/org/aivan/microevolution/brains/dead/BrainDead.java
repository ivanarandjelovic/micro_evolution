package org.aivan.microevolution.brains.dead;

import java.util.ArrayList;
import java.util.List;

import org.aivan.microevolution.actions.Action;
import org.aivan.microevolution.brains.Brain;
import org.aivan.microevolution.brains.BrainFactory;
import org.apache.log4j.Logger;

/**
 * This kind of brain is completely "dead": does not react on any input, does not produces any output/actions.
 * @author aivan
 *
 */
public class BrainDead extends Brain {

  public BrainDead(BrainFactory brainFactory) {
    super(brainFactory);
  }

  static final Logger log = Logger.getLogger(BrainDead.class);

  
  @Override
  public void tick() {
// nothing
  }

  @Override
  public List<Action> getActions() {
    return new ArrayList<Action>();
  }

  @Override
  public void foodInSight(double signalStrength) {
 // nothing
  }

  @Override
  public void predatorInSight(double signalStrength) {
 // nothing
  }

  @Override
  public void lifeFormInSight(double signalStrength) {
 // nothing
  }

  @Override
  public void hunger(double signalStrength) {
 // nothing
  }

}
