package org.aivan.microevolution.brains.dead;

import org.aivan.microevolution.brains.Brain;
import org.aivan.microevolution.brains.BrainReport;

public class BrainDeadReport implements BrainReport {

  @Override
  public void visit(Brain brain) {
    // Nothing to do here
  }

  @Override
  public String getReport() {
    return "All brains are dead!";
  }

}
