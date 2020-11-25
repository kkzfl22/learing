package com.liujun.code.refactoring.refactoring.eleven.order110.formtemplatemethod.refactor;

/**
 * @author liujun
 * @version 0.0.1
 */
public class TextStatement extends Statement {

  @Override
  public String getHead(Customer customer) {
    return "Rental Record for " + customer.getName() + "\n";
  }

  @Override
  public String getBody(Rental each) {
    return "\t" + each.getMovie().getTitle() + "\t" + each.countAmount() + "\n";
  }

  @Override
  public String getEnd(Customer customer) {
    // 执行积分计算
    int frequentRenterPoints = this.frequentRenterPointCount(customer);
    String result = " Amount owed is " + customer.countTotalAmount() + "\n";
    result += " You earned " + frequentRenterPoints + " frequent renter points";
    return result;
  }
}
