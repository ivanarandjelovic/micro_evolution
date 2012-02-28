package org.aivan.microevolution.brains.neurons;

public class NeuronConnection {
  
  // Target of this connection
  Neuron targetNeuron;
  
  // How much to make signal weaker (singal will be multiplied by this number)
  long signalStrength = 1;

  // How much of the signal gets this connection (relativ to the bias of other connections)
  long connectionBias;

  
  public NeuronConnection(Neuron targetNeuron, long signalStrength, long connectionBias) {
    super();
    this.targetNeuron = targetNeuron;
    this.signalStrength = signalStrength;
    this.connectionBias = connectionBias;
  }

  public Neuron getTargetNeuron() {
    return targetNeuron;
  }

  public void setTargetNeuron(Neuron targetNeuron) {
    this.targetNeuron = targetNeuron;
  }

  public long getSignalStrength() {
    return signalStrength;
  }

  public void setSignalStrength(long signalStrength) {
    this.signalStrength = signalStrength;
  }

  public long getConnectionBias() {
    return connectionBias;
  }

  public void setConnectionBias(long connectionBias) {
    this.connectionBias = connectionBias;
  }
  
  
}
