package org.aivan.microevolution.brains;

import java.util.ArrayList;
import java.util.List;

import org.aivan.microevolution.brains.actions.Action;
import org.aivan.microevolution.brains.actions.EatAction;
import org.aivan.microevolution.brains.actions.MoveAction;
import org.aivan.microevolution.brains.factories.BrainFactory;
import org.aivan.microevolution.brains.neurons.Neuron;
import org.aivan.microevolution.brains.neurons.NeuronTerminating;
import org.apache.log4j.Logger;

public class Brain1D extends Brain {

  private static final double MINIMAL_ACTION_POWER_LEVEL_TRASHOLD = 0.1;

  List<Neuron> entryNeurons = new ArrayList<Neuron>();

  List<NeuronTerminating> terminatingNeurons = new ArrayList<NeuronTerminating>();

  public Brain1D(BrainFactory brainFactory) {
    super(brainFactory);
  }

  static final Logger log = Logger.getLogger(Brain1D.class);

  private static final double FOOD_PERCENTAGE_ENTRY_POINT_START = 0;
  private static final double FOOD_PERCENTAGE_ENTRY_POINT_END = 24;

  private static final double PREDATOR_PERCENTAGE_ENTRY_POINT_START = 25;
  private static final double PREDATOR_PERCENTAGE_ENTRY_POINT_END = 49;

  private static final double LIFEFORM_PERCENTAGE_ENTRY_POINT_START = 50;
  private static final double LIFEFORM_PERCENTAGE_ENTRY_POINT_END = 74;

  private static final double HUNGER_PERCENTAGE_ENTRY_POINT_START = 75;
  private static final double HUNGER_PERCENTAGE_ENTRY_POINT_END = 99;

  private static final double MOVE_PERCENTAGE_ENTRY_POINT_START = 0;
  private static final double MOVE_PERCENTAGE_ENTRY_POINT_END = 49;

  private static final double EAT_PERCENTAGE_ENTRY_POINT_START = 50;
  private static final double EAT_PERCENTAGE_ENTRY_POINT_END = 99;

  double foodSignal;
  double predatorSignal;
  double lifeFormSignal;
  double hungerSignal;

  List<Action> actions = new ArrayList<Action>();

  @Override
  public void tick() {
    fireSignalInRange(foodSignal, FOOD_PERCENTAGE_ENTRY_POINT_START, FOOD_PERCENTAGE_ENTRY_POINT_END);
    fireSignalInRange(predatorSignal, PREDATOR_PERCENTAGE_ENTRY_POINT_START, PREDATOR_PERCENTAGE_ENTRY_POINT_END);
    fireSignalInRange(lifeFormSignal, LIFEFORM_PERCENTAGE_ENTRY_POINT_START, LIFEFORM_PERCENTAGE_ENTRY_POINT_END);
    fireSignalInRange(hungerSignal, HUNGER_PERCENTAGE_ENTRY_POINT_START, HUNGER_PERCENTAGE_ENTRY_POINT_END);

    calculateActions();

  }

  private void calculateActions() {
    actions.clear();
    double moveActionPower = gatherAverageSignalInRange(MOVE_PERCENTAGE_ENTRY_POINT_START, MOVE_PERCENTAGE_ENTRY_POINT_END);
    double eatActionPower = gatherAverageSignalInRange(EAT_PERCENTAGE_ENTRY_POINT_START, EAT_PERCENTAGE_ENTRY_POINT_END);

    if (moveActionPower > eatActionPower && moveActionPower > MINIMAL_ACTION_POWER_LEVEL_TRASHOLD) {
      actions.add(new MoveAction());
    } else if (eatActionPower > MINIMAL_ACTION_POWER_LEVEL_TRASHOLD) {
      actions.add(new EatAction());
    } else {
      // No actions :)
    }
  }

  @Override
  public final List<Action> getActions() {
    return actions;
  }

  @Override
  public void foodInSight(double signalStrength) {
    foodSignal = signalStrength;

  }

  @Override
  public void predatorInSight(double signalStrength) {
    predatorSignal = signalStrength;

  }

  @Override
  public void lifeFormInSight(double signalStrength) {
    lifeFormSignal = signalStrength;

  }

  @Override
  public void hunger(double signalStrength) {
    hungerSignal = signalStrength;

  }

  private void fireSignalInRange(double signalStrength, double percentageStart, double percentageEnd) {
    double percent = (entryNeurons.size() / 100.0);
    int start = (int) Math.round(Math.floor(percentageStart * percent));
    int end = (int) Math.round(Math.floor(percentageEnd * percent));
    for (int i = start; i < end; i++) {
      entryNeurons.get(i).signal(signalStrength);
    }
  }

  private double gatherAverageSignalInRange(double percentageStart, double percentageEnd) {
    double result = 0.0;
    double percent = (terminatingNeurons.size() / 100.0);
    int start = (int) Math.round(Math.floor(percentageStart * percent));
    int end = (int) Math.round(Math.floor(percentageEnd * percent));
    for (int i = start; i < end; i++) {
      result += terminatingNeurons.get(i).getReceivedSignal();
    }
    return result;
  }

}
