package org.aivan.microevolution.lifeforms;

import java.util.List;

import org.aivan.microevolution.brains.BrainFactory;
import org.aivan.microevolution.worlds.points.Point;
import org.apache.log4j.Logger;

public class SimpleLifeFormFactory extends LifeFormFactory {

  static final Logger log = Logger.getLogger(SimpleLifeFormFactory.class);

  public static final double LIFE_FORM_PERCENTAGE_IN_POINTS = 20.0;
  public static final long INITIAL_POWER_LEVEL = 50;
  public static final long MAX_LIFEFORM_AGE = 2500;
  public static final long MINIMUM_REPRODUCTION_POWER_LEVEL = INITIAL_POWER_LEVEL * 3 ;
  public static final long REPRODUCTION_POWER_COST = INITIAL_POWER_LEVEL ;

  private int formCount = 0;
  
  public SimpleLifeFormFactory() {
    super();
  }


  public SimpleLifeFormFactory(BrainFactory brainFactory) {
    super();
    this.brainFactory = brainFactory;
  }

  @Override
  public void init() {

    log.trace("init (creating initial life forms) ...");

    List<Point> points = world.getPoints();
    List<LifeForm> lifeForms = world.getLifeForms();

    while (points.size() / 100.0 * LIFE_FORM_PERCENTAGE_IN_POINTS > lifeForms.size()) {
      int rand = (int) Math.floor(Math.random() * points.size());
      Point point = points.get(rand);
      LifeForm lifeForm = new LifeForm(this, getNextLifeFormCounter(), brainFactory.createNew(), INITIAL_POWER_LEVEL, MAX_LIFEFORM_AGE,
          MINIMUM_REPRODUCTION_POWER_LEVEL, REPRODUCTION_POWER_COST);
      point.lifeFormEntered(lifeForm);
      world.getLifeForms().add(lifeForm);
      formCount++;
    }
    log.trace("init done, life forms created: " + formCount);

  }

}
