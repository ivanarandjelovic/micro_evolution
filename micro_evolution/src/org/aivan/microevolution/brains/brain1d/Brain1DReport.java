package org.aivan.microevolution.brains.brain1d;

import java.text.DecimalFormat;
import java.util.Formatter;

import org.aivan.microevolution.brains.Brain;
import org.aivan.microevolution.brains.BrainReport;
import org.aivan.microevolution.brains.neurons.NeuronConnected;
import org.aivan.microevolution.brains.neurons.NeuronConnection;
import org.apache.commons.math.stat.descriptive.SummaryStatistics;

public class Brain1DReport implements BrainReport {

  SummaryStatistics[][] biases = new SummaryStatistics[Brain1DFactory.NUMBER_OF_ENTRY_NEURONS][Brain1DFactory.NUMBER_OF_TERMINATING_NEURONS];
  SummaryStatistics[][] modifiers = new SummaryStatistics[Brain1DFactory.NUMBER_OF_ENTRY_NEURONS][Brain1DFactory.NUMBER_OF_TERMINATING_NEURONS];

  {

  }

  long connectionCount = 0;

  public Brain1DReport() {
    super();
    for (int i = 0; i < biases.length; i++) {
      for (int j = 0; j < biases[i].length; j++) {
        biases[i][j] = new SummaryStatistics();
        modifiers[i][j] = new SummaryStatistics();
      }
    }
  }

  public void visit(Brain brain) {
    Brain1D brain1d = (Brain1D) brain;

    for (int entryCount = 0; entryCount < brain1d.getEntryNeurons().size(); entryCount++) {
      NeuronConnected entryNeuron = brain1d.getEntryNeurons().get(entryCount);
      for (int connCount = 0; connCount < entryNeuron.getNeuronConnections().size(); connCount++) {
        NeuronConnection conn = entryNeuron.getNeuronConnections().get(connCount);
        biases[entryCount][connCount].addValue(conn.getConnectionBias());
        modifiers[entryCount][connCount].addValue(conn.getSignalModifier());
      }
    }

    connectionCount++;
  }

  public String getReport() {
    String report = "Values (bias / modifier):\n";
    DecimalFormat df = new DecimalFormat(" 0.0000000000;-0.0000000000");
    Formatter fmt = new Formatter();

    // TODO: fix this to work with more terminating neurons

    String format = "%1$20s %2$25s %3$25s\n";
    fmt.format(format, "Entry neuron", "Move neuron", "Eat target");
    for (int i = 0; i < biases.length; i++) {
      String entryName = "unknown";
      if (i == 0) {
        entryName = "food";
      } else if (i == 1) {
        entryName = "predator";
      } else if (i == 2) {
        entryName = "lifeForm";
      } else if (i == 3) {
        entryName = "hunger";
      }
      fmt.format(format, entryName, df.format(biases[i][0].getMean()) + "/" + df.format(modifiers[i][0].getMean()),
          df.format(biases[i][1].getMean()) + "/" + df.format(modifiers[i][1].getMean()));
    }
    report += fmt.toString();

    fmt = new Formatter();
    report += " Deviations (bias / modified): \n";
    fmt.format(format, "Entry neuron", "Move neuron", "Eat target");
    for (int i = 0; i < biases.length; i++) {
      String entryName = "unknown";
      if (i == 0) {
        entryName = "food";
      } else if (i == 1) {
        entryName = "predator";
      } else if (i == 2) {
        entryName = "lifeForm";
      } else if (i == 3) {
        entryName = "hunger";
      }
      fmt.format(format, entryName,
          df.format(biases[i][0].getStandardDeviation()) + "/" + df.format(modifiers[i][0].getStandardDeviation()),
          df.format(biases[i][1].getStandardDeviation()) + "/" + df.format(modifiers[i][1].getStandardDeviation()));
    }
    report += fmt.toString();

    return report;
  }
}
