package org.aivan.microevolution.brains.dummy;

import org.aivan.microevolution.brains.Brain;
import org.aivan.microevolution.brains.BrainReport;

public class DummyBrainReport implements BrainReport {

  @Override
  public void visit(Brain brain) {
    // nothing to do, all are same
  }

  @Override
  public String getReport() {
    return "dummy brains are all the same: random brains.";
  }

}
