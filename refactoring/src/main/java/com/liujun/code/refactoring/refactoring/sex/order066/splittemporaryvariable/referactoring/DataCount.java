package com.liujun.code.refactoring.refactoring.sex.order066.splittemporaryvariable.referactoring;

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
    // 继续重构，去除临时变量
    int primaryTime = Math.min(time, delay);
    result = 0.5 * primaryAcc() * primaryTime + primaryTime;
    int secondaryTime = time - delay;
    if (secondaryTime > 0) {
      // 同样去除临时变量
      result += primaryVel() * secondaryTime + 0.5 * secondaryAcc() * secondaryTime * secondaryTime;
    }

    return result;
  }

  private double primaryAcc() {
    return primaryForce * mass;
  }

  private double primaryVel() {
    return primaryAcc() * delay;
  }

  private double secondaryAcc() {
    return (primaryForce + secondaryForce) / mass;
  }
}
