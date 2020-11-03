package com.liujun.code.refactoring.refactoring.seven.order074.inlineclass.src;

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

  /** @return */
  public TelephoneNumber getOfficeTelephone() {
    return telephoneNumber;
  }
}
