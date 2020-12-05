package com.liujun.legacy.code.parttwo.order208.addfunction.tdd.tdd2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liujun
 * @version 0.0.1
 */
public class InstrumentCalculator {

  private List<Double> elements = new ArrayList<>(8);

  public void addElement(double value) {
    this.elements.add(value);
  }

  public double firstMomentAbout(double point) throws InvalidBasisException {

    if (null == elements || elements.isEmpty()) {
      throw new InvalidBasisException("no elements");
    }

    double numerator = 0.0;
    for (Double element : elements) {
      double elementValue = element.doubleValue();
      numerator += elementValue - point;
    }

    return numerator / elements.size();
  }
}
