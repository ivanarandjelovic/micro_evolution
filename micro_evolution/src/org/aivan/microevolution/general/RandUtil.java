package org.aivan.microevolution.general;

public class RandUtil {

  public static double toRange(double randomDouble, double minValue, double maxValue) {
    return (randomDouble*(maxValue-minValue))+minValue;
  }
}
