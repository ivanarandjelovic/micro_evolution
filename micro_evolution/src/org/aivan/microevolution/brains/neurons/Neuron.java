package org.aivan.microevolution.brains.neurons;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public abstract class Neuron {

  static final Logger log = Logger.getLogger(Neuron.class);

  List<NeuronConnection> neuronConnections = new ArrayList<NeuronConnection>();

  public Neuron() {
    super();
    // TODO Auto-generated constructor stub
  }

  public List<NeuronConnection> getNeuronConnections() {
    return neuronConnections;
  }

  public void setNeuronConnections(List<NeuronConnection> neuronConnections) {
    this.neuronConnections = neuronConnections;
  }
  
  

}
