package com.liujun.code.refactoring.refactoring.seven.order073.extractclass.refactoring2;

/**
 * 示例代码。
 *
 * @author liujun
 * @version 0.0.1
 */
public class Person {

  /** 名称 */
  private String name;

  /** 电话号码 */
  private TelephoneNumber telephoneNumber = new TelephoneNumber();

  public Person(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public String getTelephoneNumber() {
    return "(" + telephoneNumber.getOfficeAreaCode() + ")" + telephoneNumber.getOfficeNumber();
  }

  public void setOfficeAreaCode(String officeAreaCode) {
    this.telephoneNumber.setOfficeAreaCode(officeAreaCode);
  }

  public void setOfficeNumber(String officeNumber) {
    this.telephoneNumber.setOfficeNumber(officeNumber);
  }
}
