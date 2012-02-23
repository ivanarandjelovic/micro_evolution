package org.aivan.microevolution.lifeforms.factories;

import java.util.List;

import org.aivan.microevolution.brains.DummyBrain;
import org.aivan.microevolution.lifeforms.Bug1;
import org.aivan.microevolution.lifeforms.LifeForm;
import org.aivan.microevolution.worlds.points.Point;
import org.apache.log4j.Logger;

public class SimpleLifeFormFactory extends LifeFormFactory {

  static final Logger log = Logger.getLogger(SimpleLifeFormFactory.class);

  public static final double LIFE_FORM_PERCENTAGE_IN_POINTS = 10.0;

  private int formCount = 0;

  @Override
  public void init() {

    log.debug("init (creating initial life forms) ...");

    List<Point> points = world.getPoints();
    List<LifeForm> lifeForms = world.getLifeForms();

    while (points.size() / 100.0 * LIFE_FORM_PERCENTAGE_IN_POINTS > lifeForms.size()) {
      int rand = (int) Math.floor(Math.random() * points.size());
      Point point = points.get(rand);
      LifeForm lifeForm = new Bug1(getNextLifeFormCounter(), new DummyBrain());
      point.lifeFormEntered(lifeForm);
      world.getLifeForms().add(lifeForm);
      formCount++;
    }
    log.debug("init done, life forms created: " + formCount);

  }

}
