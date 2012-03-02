package org.aivan.microevolution.tmp;

import org.apache.commons.math.stat.descriptive.SummaryStatistics;

public class StatisticsTest {

  /**
   * @param args
   */
  public static void main(String[] args) {
    SummaryStatistics stat = new SummaryStatistics();
    stat.addValue(1.0);
    stat.addValue(2.0);
    stat.addValue(3.5);
    
    System.out.println("Mean: "+stat.getMean());
    System.out.println("standard dev: "+stat.getStandardDeviation());
    System.out.println("variance dev: "+stat.getVariance());
    }

}
