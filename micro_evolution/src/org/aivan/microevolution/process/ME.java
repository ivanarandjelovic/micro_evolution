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

  public void runWorld(long ticks, long reportOnTicks) throws InterruptedException {
    log.info("Report at START: ");
    log.info("================ ");
    log.info("\n" + this.getGeneralReport());

    log.debug("Ticking world for: " + ticks + " ticks ...");
    for (long i = 0; i < ticks; i++) {
      if (i % 10 == 0) {
        log.debug("Ticking: " + i + " ticking ...");
      }
      world.tick();
      if (reportOnTicks > 0 && i % reportOnTicks == 0 && i > 0 && i < (ticks - 1)) {
        log.info(getGeneralReport());
      }

      if (world.getLifeForms().size() == 0) {
        log.info("Stopping evolution, all life forms dead. Current tick count: " + i + " of " + ticks);
        break;
      }

//      if (bigReportOnTicks > 0 && i % bigReportOnTicks == 0 && i > 0 && i < (ticks - 1)) {
//        log.info("Report during evolution on each " + bigReportOnTicks + " ticks: ");
//        log.info("================ ");
//        log.info("\n" + this.getReport());
//      }

    }

    log.info("Report at END: ");
    log.info("================ ");
    log.info("\n" + this.getGeneralReport());

    log.info("Life forms death reasons: ");
    long predatorDeath = 0;
    long hungerDeath = 0;
    long ageDeath = 0;
    long unknownReason = 0;
    for(LifeForm lifeForm : world.getDeadLifeForms()) {
      if(lifeForm.getDiedFrom() == LifeForm.DeathReason.PREDATOR) {
        predatorDeath++;
      } else if (lifeForm.getDiedFrom() == LifeForm.DeathReason.HUNGER){
        hungerDeath ++;
      } else if (lifeForm.getDiedFrom() == LifeForm.DeathReason.AGE){
        ageDeath ++;
      } else {
        unknownReason++;
      }
    }
    log.info("Died from predator: "+predatorDeath);
    log.info("Died from hunger  : "+hungerDeath);
    log.info("Died from age     : "+ageDeath);
    log.info("Died from unknown : "+unknownReason);
    
    world.shutdown();

  }

  public String getReport() {
    String report = "";

    report += getGeneralReport();
    if (world.getPoints().size() > 1000) {
      report += "\nDetailed placement report not availabe when number of points > 1000";
    } else {
      report += "\nWorld's lifeForm placement:" + world.getLifeFormReport();
      report += "\nWorld's food placement:" + world.getFoodReport();
      report += "\nWorld's predator placement:" + world.getPredatorReport();
      report += "\nWorld's life forms and food placement:" + world.getLifeFormAndFoodAndPredatorReport();
      report += "\n:LifeForm details:";
      for (LifeForm lifeForm : world.getLifeForms()) {
        report += "\n" + lifeForm.getReport();
      }
    }

    return report;
  }

  private String getGeneralReport() {
    String report = "";
    report += "World : " + world.getClass().getName();
    report += "\nWorld point count: " + world.getPoints().size();
    report += "\nWorld's tick count: " + world.getTickCounter();
    report += "\nWorld's lifeForm count: " + world.getLifeForms().size();
    report += "\nWorld's food count: " + world.getFood().size();
    report += "\nWorld's predator count: " + world.getPredators().size();
    report += "\nWorld's deadLifeForm count: " + world.getDeadLifeForms().size();
    report += "\nBrain report:\n"+world.getBrainReport();
    return report;
  }
}
