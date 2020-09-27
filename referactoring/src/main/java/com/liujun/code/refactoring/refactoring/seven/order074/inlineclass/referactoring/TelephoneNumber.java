package com.liujun.code.refactoring.refactoring.seven.order074.inlineclass.referactoring;

import lombok.ToString;

/**
 * 首先分离中电话号码这个类
 *
 * @author liujun
 * @version 0.0.1
 */
@ToString
public class TelephoneNumber {

  private String officeAreaCode;

  private String officeNumber;

  public String getTelephoneNumber() {
    return "(" + getOfficeAreaCode() + ")" + getOfficeNumber();
  }


  public String getOfficeAreaCode() {
    return officeAreaCode;
  }

  public void setOfficeAreaCode(String officeAreaCode) {
    this.officeAreaCode = officeAreaCode;
  }

  public String getOfficeNumber() {
    return officeNumber;
  }

  public void setOfficeNumber(String officeNumber) {
    this.officeNumber = officeNumber;
  }
}
