package org.aivan.microevolution.lifeforms.predators;

import java.util.List;

import org.aivan.microevolution.general.Tickable;
import org.aivan.microevolution.worlds.World;
import org.aivan.microevolution.worlds.points.Point;
import org.apache.log4j.Logger;

public class ConstantPredatorFactory extends PredatorFactory implements Tickable {

  static final Logger log = Logger.getLogger(ConstantPredatorFactory.class);

  public static final double PREDATOR_PER_POINT_PER_TICK = 0.001;
  private static final int PREDATOR_DURATION = 3;

  private double currentPredatorFactor = 0.0;

  public ConstantPredatorFactory(World world) {
    super(world);
  }

  @Override
  public void tick() {
    //log.trace("tick ...");

    List<Point> points = world.getPoints();
    long pointCount = points.size();

    currentPredatorFactor += (pointCount * PREDATOR_PER_POINT_PER_TICK);

    if (currentPredatorFactor >= 1.0) {
      //log.trace("Creating new predators ...");
      while (currentPredatorFactor >= 1.0) {
        generateOnePredator(points, pointCount);
        currentPredatorFactor -= 1.0;
      }
    }
  }

  private void generateOnePredator(List<Point> points, long pointCount) {
    Predator predator = new Predator1(getNextPredatorCounter(), PREDATOR_DURATION);
    int rand = (int) Math.floor(Math.random() * pointCount);
    Point point = points.get(rand);
    if (point.getPredator() == null) {
      //log.trace("Point has no predator, adding it");
      point.setPredator(predator);
    } else {
      //log.trace("Point has predator already, skipping.");
    }

  }

  @Override
  public void init() {
    //log.trace("init...");

    // Nothing to do initially
  }

}
