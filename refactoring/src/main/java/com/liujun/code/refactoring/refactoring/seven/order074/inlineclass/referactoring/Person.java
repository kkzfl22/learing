package com.liujun.code.refactoring.refactoring.seven.order074.inlineclass.referactoring;

/**
 * 示例代码。
 *
 * @author liujun
 * @version 0.0.1
 */
public class Person {

  /** 名称 */
  private String name;

  private String officeAreaCode;

  private String officeNumber;

  public Person(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public String getTelephoneNumber() {
    return "(" + this.getOfficeAreaCode() + ")" + this.getOfficeNumber();
  }

  public String getOfficeAreaCode() {
    return this.officeAreaCode;
  }

  public void setOfficeAreaCode(String officeAreaCode) {
    this.officeAreaCode = officeAreaCode;
  }

  public String getOfficeNumber() {
    return this.officeNumber;
  }

  public void setOfficeNumber(String officeNumber) {
    this.officeNumber = officeNumber;
  }
}
