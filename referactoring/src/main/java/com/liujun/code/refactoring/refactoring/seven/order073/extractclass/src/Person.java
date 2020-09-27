package com.liujun.code.refactoring.refactoring.seven.order073.extractclass.src;

/**
 * 示例代码。
 *
 * @author liujun
 * @version 0.0.1
 */
public class Person {

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
    return "(" + officeAreaCode + ")" + officeNumber;
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
