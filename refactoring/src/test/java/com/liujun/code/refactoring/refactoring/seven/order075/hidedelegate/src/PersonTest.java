package com.liujun.code.refactoring.refactoring.seven.order075.hidedelegate.src;

import org.junit.Test;

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

    // 如果客户希望知道某人的经理是谁，就必须取得Department对象。
    person.getDepartment().getManager();
  }
}
