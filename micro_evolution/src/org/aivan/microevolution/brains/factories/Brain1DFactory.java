package org.aivan.microevolution.brains.factories;

import java.util.Random;

import org.aivan.microevolution.brains.Brain;
import org.aivan.microevolution.brains.Brain1D;
import org.aivan.microevolution.brains.neurons.Neuron;
import org.aivan.microevolution.brains.neurons.NeuronConnected;
import org.aivan.microevolution.brains.neurons.NeuronConnection;
import org.aivan.microevolution.brains.neurons.NeuronTerminating;
import org.aivan.microevolution.general.RandUtil;

public class Brain1DFactory implements BrainFactory {

  private static final double MIN_SIGNAL_MODIFIER = -1.0;
  private static final double MAX_SIGNAL_MODIFIER = 1.0;
  private static final int NUMBER_OF_TERMINATING_NEURONS = 2;
  private static final int NUMBER_OF_ENTRY_NEURONS = 4;

  private Random random = new Random();

  @Override
  public Brain createNew() {
    Brain1D brain = new Brain1D(this);

    // Simple terminating neurons
    for (int i = 0; i < NUMBER_OF_TERMINATING_NEURONS; i++) {
      brain.getTerminatingNeurons().add(new NeuronTerminating());
    }

    // Simple connected neurons, one connection for each terminating neuron
    // All of this with random bias and random signal modifier
    for (int i = 0; i < NUMBER_OF_ENTRY_NEURONS; i++) {
      NeuronConnected entryNeuron = new NeuronConnected();
      for (Neuron neuron : brain.getTerminatingNeurons()) {
        NeuronConnection neuronConnection = new NeuronConnection(neuron, RandUtil.toRange(random.nextDouble(), MIN_SIGNAL_MODIFIER,
            MAX_SIGNAL_MODIFIER), random.nextDouble());
        entryNeuron.addNeuronConnection(neuronConnection);
      }
      brain.getEntryNeurons().add(entryNeuron);
    }
    return brain;
  }

  @Override
  public Brain combine(Brain brain1, Brain brain2) {
    Brain1D brain = new Brain1D(this);

    Brain1D parent1 = (Brain1D) brain1;
    Brain1D parent2 = (Brain1D) brain2;

    // Simple terminating neurons
    for (int i = 0; i < NUMBER_OF_TERMINATING_NEURONS; i++) {
      brain.getTerminatingNeurons().add(new NeuronTerminating());
    }

    // Simple connected neurons, one connection for each terminating neuron
    // bias and signal modifier are arithmetic middle of parent brains
    for (int i = 0; i < NUMBER_OF_ENTRY_NEURONS; i++) {
      NeuronConnected entryNeuron = new NeuronConnected();

      NeuronConnected parent1Neuron = parent1.getEntryNeurons().get(i);
      NeuronConnected parent2Neuron = parent2.getEntryNeurons().get(i);

      for (int terminatingCount = 0; terminatingCount < brain.getTerminatingNeurons().size(); terminatingCount++) {
        Neuron neuron = brain.getTerminatingNeurons().get(terminatingCount);

        NeuronConnection neuronCon1 = parent1Neuron.getNeuronConnections().get(terminatingCount);
        NeuronConnection neuronCon2 = parent2Neuron.getNeuronConnections().get(terminatingCount);
        
        double bias = (neuronCon1.getConnectionBias() + neuronCon2.getConnectionBias()) / 2.0;
        double modifier = (neuronCon1.getSignalModifier() + neuronCon2.getSignalModifier()) / 2.0;
        
        NeuronConnection neuronConnection = new NeuronConnection(neuron, modifier, bias);
        
        entryNeuron.addNeuronConnection(neuronConnection);
      }
      brain.getEntryNeurons().add(entryNeuron);
    }

    return brain;
  }

}
