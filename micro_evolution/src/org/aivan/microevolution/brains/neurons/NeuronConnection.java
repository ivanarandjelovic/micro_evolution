package org.aivan.microevolution.brains.neurons;

public class NeuronConnection {

  // Target of this connection
  Neuron targetNeuron;

  // How much of the signal gets this connection (relativ to the bias of other
  // connections)
  double connectionBias;

  // How much to make signal weaker (singal will be multiplied by this number)
  // The value can be from -1 to 1 where meaning is following:
  // -1 send (1- "signal strength"),
  // -0.x send 0.x * (1 - "signal strength")
  // 0 - do not 'send anything"
  // 0.x send 0.x * "signal strength"
  // 1 send "signal strength"
  double signalModifier = 1;

  public NeuronConnection(Neuron targetNeuron, double signalModifier, double connectionBias) {
    super();
    this.targetNeuron = targetNeuron;
    this.signalModifier = signalModifier;
    this.connectionBias = connectionBias;
  }

  public Neuron getTargetNeuron() {
    return targetNeuron;
  }

  public void setSignalStrength(long signalStrength) {
    this.signalModifier = signalStrength;
  }

  public double getConnectionBias() {
    return connectionBias;
  }

  public double getSignalModifier() {
    return signalModifier;
  }

  void signal(double signal) {
    double resultSignal;
    assert ((signal >= 0) && (signal <= 1));

    if (signalModifier >= 0) {
      resultSignal = signalModifier * signal;
    } else {
      resultSignal = Math.abs(signalModifier * (1.0 - signal));
    }
    targetNeuron.signal(resultSignal);
  }
}
