package org.aivan.microevolution.brains.neurons;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public abstract class NeuronConnected implements Neuron {

  static final Logger log = Logger.getLogger(NeuronConnected.class);

  List<NeuronConnection> neuronConnections = new ArrayList<NeuronConnection>();

  public NeuronConnected() {
    super();
  }

  public List<NeuronConnection> getNeuronConnections() {
    return neuronConnections;
  }

  public void setNeuronConnections(List<NeuronConnection> neuronConnections) {
    this.neuronConnections = neuronConnections;
  }
  
  @Override
  public void signal(double signal) {
    // TODO
  }

}
