package org.aivan.microevolution.tmp;

import java.text.DecimalFormat;

import org.aivan.microevolution.brains.neurons.NeuronConnection;
import org.aivan.microevolution.brains.neurons.NeuronTerminating;

public class NuronConnectionTest {

  /**
   * @param args
   */
  public static void main(String[] args) {
    DecimalFormat df = new DecimalFormat(" 0.0000000000;-0.0000000000");
    for (int j = 0; j <= 10; j++) {
      double signal = j * 0.1;
      System.out.println("\nResults for signal: " + signal);
      for (int i = -10; i <= 10; i++) {
        double modifier = i * 0.1;
        NeuronConnection connection = new NeuronConnection(new NeuronTerminating(), modifier);
        connection.signal(signal);
        System.out.println("modifier: " + df.format(modifier) + " result: "
            + df.format(((NeuronTerminating) connection.getTargetNeuron()).getReceivedSignal()));
      }
    }
  }

}
