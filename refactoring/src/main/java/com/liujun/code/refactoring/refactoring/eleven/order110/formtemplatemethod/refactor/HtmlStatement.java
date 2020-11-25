package com.liujun.code.refactoring.refactoring.eleven.order110.formtemplatemethod.refactor;

/**
 * @author liujun
 * @version 0.0.1
 */
public class HtmlStatement extends Statement {

  @Override
  protected String getHead(Customer customer) {
    return "<H1>Rental Record for <EM> " + customer.getName() + "</EM><H1><P>n";
  }

  @Override
  protected String getBody(Rental each) {
    return "\t" + each.getMovie().getTitle() + "\t" + each.countAmount() + "<BR>\n";
  }

  @Override
  protected String getEnd(Customer customer) {
    int frequentRenterPoints = this.frequentRenterPointCount(customer);
    String result = "<P> Amount owed is <EM>" + customer.countTotalAmount() + "</EM><P>\n";
    result += " You earned <EM>" + frequentRenterPoints + " </EM> frequent renter points <P>";
    return result;
  }
}
