package com.liujun.code.refactoring.refactoring.eleven.order110.formtemplatemethod.refactor;

/**
 * @author liujun
 * @version 0.0.1
 */
public abstract class Statement {

  /**
   * 生成详单的函数
   *
   * @return
   */
  public String value(Customer customer) {

    String result = this.getHead(customer);
    for (Rental each : customer.getRentals()) {
      result += this.getBody(each);
    }
    result += getEnd(customer);
    return result;
  }

  /**
   * 积分计算
   *
   * @param customer
   * @return
   */
  protected int frequentRenterPointCount(Customer customer) {
    int frequentRenterPoints = 0;
    for (Rental each : customer.getRentals()) {
      // 积分计算的方法
      frequentRenterPoints += each.frequentRenterCount();
    }

    return frequentRenterPoints;
  }

  /**
   * 添加消息头的公共函数
   *
   * @param customer
   * @return
   */
  protected abstract String getHead(Customer customer);

  /**
   * 内容信息
   *
   * @param customer
   * @return
   */
  protected abstract String getBody(Rental customer);

  /**
   * 结果信息
   *
   * @param customer
   * @return
   */
  protected abstract String getEnd(Customer customer);
}
