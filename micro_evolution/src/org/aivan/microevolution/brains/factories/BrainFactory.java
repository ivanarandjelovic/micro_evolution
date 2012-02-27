package org.aivan.microevolution.brains.factories;

import org.aivan.microevolution.brains.Brain;

public interface BrainFactory {

   public Brain createNew();

   public Brain combine(Brain brain1, Brain brain2);
   
}
