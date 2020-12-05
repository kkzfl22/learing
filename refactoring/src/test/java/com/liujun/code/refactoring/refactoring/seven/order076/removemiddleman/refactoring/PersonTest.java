package com.liujun.code.refactoring.refactoring.seven.order076.removemiddleman.refactoring;

import org.junit.jupiter.api.Test;

/**
 * @author liujun
 * @version 0.0.1
 */
public class PersonTest {

  @Test
  public void getManager() {
    Person person = new Person();
    Department departMent = new Department(person);
    departMent.setChargeCode("abc");
    person.setDepartment(departMent);

    // 移除中间的代理，改为直接调用
    person.getDepartment().getManager();
  }
}
