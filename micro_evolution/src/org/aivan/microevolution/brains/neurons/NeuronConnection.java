package org.aivan.microevolution.brains.neurons;

public class NeuronConnection {

  // Target of this connection
  Neuron targetNeuron;

  // How much to make signal weaker (singal will be multiplied by this number)
  // The value can be from -1 to 1 where meaning is following:
  // -1 send (1- "signal strength"),
  // -0.x send 0.x * (1 - "signal strength")
  // 0 - do not 'send anything"
  // 0.x send 0.x * "signal strength"
  // 1 send "signal strength"
  double signalModifier = 1;

  public NeuronConnection(Neuron targetNeuron, double signalModifier) {
    super();
    this.targetNeuron = targetNeuron;
    this.signalModifier = signalModifier;
  }

  public Neuron getTargetNeuron() {
    return targetNeuron;
  }

  public void setSignalStrength(long signalStrength) {
    this.signalModifier = signalStrength;
  }

  public double getSignalModifier() {
    return signalModifier;
  }

  public void signal(double signal) {
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
