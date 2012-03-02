package org.aivan.microevolution.brains.neurons;

public class NeuronTerminating implements Neuron {

	double receivedSignal;

	public double getReceivedSignal() {
		return receivedSignal;
	}

	@Override
	public void signal(double signal) {
		receivedSignal = signal;
	}

}
