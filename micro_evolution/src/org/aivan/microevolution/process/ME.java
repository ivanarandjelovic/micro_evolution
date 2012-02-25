package org.aivan.microevolution.process;

import org.aivan.microevolution.lifeforms.LifeForm;
import org.aivan.microevolution.worlds.World;
import org.apache.log4j.Logger;

/**
 * Main class for starting and executing evolutions.
 * 
 * @author iarandjelovic
 * 
 */
public class ME {

  static final Logger log = Logger.getLogger(ME.class);

  World world = null;

  public ME(World world) {
    super();
    log.debug("MicroEvolution created, world: " + world);
    this.world = world;
    log.debug("Initializing world ...");
    world.init();
  }

  public void runWorld(long ticks, long reportOnTicks) {
    log.info("Report at START: ");
    log.info("================ ");
    log.info("\n" + this.getReport());

    log.debug("Ticking world for: " + ticks + " ticks ...");
    for (long i = 0; i < ticks; i++) {
      log.debug("Tick: " + i + " ticking ...");
      world.tick();
      log.debug(getGeneralReport());
      if (world.getLifeForms().size() == 0) {
        log.info("Stopping evolution, all life forms dead. Current tick count: " + i + " of " + ticks);
        break;
      }

      if (i % reportOnTicks == 0 && i > 0 && i < (ticks - 1)) {
        log.info("Report during evolution on each " + reportOnTicks + " ticks: ");
        log.info("================ ");
        log.info("\n" + this.getReport());
      }

    }

    log.info("Report at END: ");
    log.info("================ ");
    log.info("\n" + this.getReport());

  }

  public String getReport() {
    String report = "";

    report += "World : " + world.getClass().getName();
    report += getGeneralReport();
    report += "\nWorld's lifeForm placement:" + world.getLifeFormReport();
    report += "\nWorld's food placement:" + world.getFoodReport();
    report += "\nWorld's life forms and food placement:" + world.getLifeFormAndFoodReport();
    report += "\n:LifeForm details:";
    for (LifeForm lifeForm : world.getLifeForms()) {
      report += "\n" + lifeForm.getReport();
    }

    return report;
  }

  private String getGeneralReport() {
    String report = "";
    report += "\nWorld's tick count: " + world.getTickCounter();
    report += "\nWorld's lifeForm count: " + world.getLifeForms().size();
    report += "\nWorld's food count: " + world.getFood().size();
    report += "\nWorld's deadLifeForm count: " + world.getDeadLifeForms().size();
    return report;
  }
}
