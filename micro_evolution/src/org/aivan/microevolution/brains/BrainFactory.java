package org.aivan.microevolution.brains;


public interface BrainFactory {

   public Brain createNew();

   public Brain combine(Brain brain1, Brain brain2);
   
   public BrainReport createNewBrainReport();
}
