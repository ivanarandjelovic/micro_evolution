package org.aivan.microevolution.brains;

import java.util.ArrayList;
import java.util.List;

import org.aivan.microevolution.brains.actions.Action;
import org.aivan.microevolution.brains.factories.BrainFactory;
import org.apache.log4j.Logger;

public class Brain1D extends Brain {

  public Brain1D(BrainFactory brainFactory) {
    super(brainFactory);
  }

  static final Logger log = Logger.getLogger(Brain1D.class);

  
  @Override
  public void tick() {
    //log.trace("tick...");
    // TODO Auto-generated method stub

  }

  @Override
  public List<Action> getActions() {
    // TODO Auto-generated method stub
    return new ArrayList<Action>();
  }

  @Override
  public void foodInSight(double signalStrength) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void predatorInSight(double signalStrength) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void lifeFormInSight(double signalStrength) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void hunger(double signalStrength) {
    // TODO Auto-generated method stub
    
  }

}
