package org.aivan.microevolution.runs;

import org.aivan.microevolution.brains.BrainFactory;
import org.aivan.microevolution.food.FoodFactory;
import org.aivan.microevolution.lifeforms.LifeFormFactory;
import org.aivan.microevolution.predators.PredatorFactory;

public class WorlRunConfiguration {

  private long worldPointCount;
  private FoodFactory foodFactory;
  private LifeFormFactory lifeFormFactory;
  private BrainFactory brainFactory;
  private PredatorFactory predatorFactory;
  private long ticksToRun;
  private long reportOnTicks;
  private long repeatRuns = 1;
  private long brainReportOnTicks = 0;

  public long getWorldPointCount() {
    return worldPointCount;
  }

  public void setWorldPointCount(long worldPointCount) {
    this.worldPointCount = worldPointCount;
  }

  public FoodFactory getFoodFactory() {
    return foodFactory;
  }

  public void setFoodFactory(FoodFactory foodFactory) {
    this.foodFactory = foodFactory;
  }

  public LifeFormFactory getLifeFormFactory() {
    return lifeFormFactory;
  }

  public void setLifeFormFactory(LifeFormFactory lifeFormFactory) {
    this.lifeFormFactory = lifeFormFactory;
  }

  public BrainFactory getBrainFactory() {
    return brainFactory;
  }

  public void setBrainFactory(BrainFactory brainFactory) {
    this.brainFactory = brainFactory;
  }

  public PredatorFactory getPredatorFactory() {
    return predatorFactory;
  }

  public void setPredatorFactory(PredatorFactory predatorFactory) {
    this.predatorFactory = predatorFactory;
  }

  public long getTicksToRun() {
    return ticksToRun;
  }

  public void setTicksToRun(long ticksToRun) {
    this.ticksToRun = ticksToRun;
  }

  public long getReportOnTicks() {
    return reportOnTicks;
  }

  public void setReportOnTicks(long reportOnTicks) {
    this.reportOnTicks = reportOnTicks;
  }

  public long getRepeatRuns() {
    return repeatRuns;
  }

  public void setRepeatRuns(long repeatRuns) {
    this.repeatRuns = repeatRuns;
  }

  public long getBrainReportOnTicks() {
    return brainReportOnTicks;
  }

  public void setBrainReportOnTicks(long brainReportOnTicks) {
    this.brainReportOnTicks = brainReportOnTicks;
  }

}
