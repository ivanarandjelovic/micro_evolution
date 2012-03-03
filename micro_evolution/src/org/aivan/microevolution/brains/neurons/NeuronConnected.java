package org.aivan.microevolution.brains.neurons;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class NeuronConnected implements Neuron {

  static final Logger log = Logger.getLogger(NeuronConnected.class);

  List<NeuronConnection> neuronConnections = new ArrayList<NeuronConnection>();

  public NeuronConnected() {
    super();
  }

  public List<NeuronConnection> getNeuronConnections() {
    return neuronConnections;
  }

  public void addNeuronConnection(NeuronConnection neuronConnection) {
    this.neuronConnections.add(neuronConnection);
  }

  @Override
  public void signal(double signal) {
    for(NeuronConnection connection : neuronConnections) {
      connection.signal(signal);
    }
  }

}
