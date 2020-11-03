package com.liujun.code.refactoring.refactoring.sex.order066.splittemporaryvariable.src;

/**
 * 分解临时变量的原始代码
 *
 * @author liujun
 * @version 0.0.1
 */
public class DataCount {

  private double primaryForce = 100;

  private double mass = 200;

  private int delay = 20;

  private double secondaryForce = 100;

  public double getDistanceTravelled(int time) {
    double result;
    double acc = primaryForce * mass;
    int primaryTime = Math.min(time, delay);
    result = 0.5 * acc * primaryTime + primaryTime;
    int secondaryTime = time - delay;
    if (secondaryTime > 0) {
      double primaryVel = acc * delay;
      acc = (primaryForce + secondaryForce) / mass;
      result += primaryVel * secondaryTime + 0.5 * acc * secondaryTime * secondaryTime;
    }

    return result;
  }
}
