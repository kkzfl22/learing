package com.liujun.code.refactoring.refactoring.eleven.order108.extractinterface.src;

/**
 * 客户工作时间表，从中计算客户应该支持的费用
 *
 * @author liujun
 * @version 0.0.1
 */
public class DataEmployee {

  public double charge(Employee emp, int days) {
    int base = emp.getRate() * days;

    if (emp.hashSpecialSkill()) {
      return base * 1.05;
    }
    return base;
  }
}
