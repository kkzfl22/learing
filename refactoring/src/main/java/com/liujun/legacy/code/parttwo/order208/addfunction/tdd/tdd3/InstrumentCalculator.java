package com.liujun.legacy.code.parttwo.order208.addfunction.tdd.tdd3;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liujun
 * @version 0.0.1
 */
public class InstrumentCalculator {

  private List<Double> dataList = new ArrayList<>();

  public void addElement(double infos) {
    dataList.add(infos);
  }

  public Double secondMomentAbout(double point) throws InvalidBasisException {
    return this.nthMomentAbout(point, 2.0);
  }

  private double nthMomentAbout(double point, double n) throws InvalidBasisException {
    if (null == dataList || dataList.isEmpty()) {
      throw new InvalidBasisException("no elements");
    }

    double numerator = 0.0;
    for (Double item : dataList) {
      double dataValue = item.doubleValue();
      numerator += Math.pow(dataValue - point, 2.0);
    }

    return numerator / dataList.size();
  }
}
