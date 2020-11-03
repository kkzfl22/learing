package com.liujun.code.refactoring.refactoring.seven.order073.extractclass.refactoring2;

import org.junit.Assert;
import org.junit.Test;

/**
 * 进行测试
 *
 * @author liujun
 * @version 0.0.1
 */
public class PersonTest {

  @Test
  public void testPerson() {
    Person person = new Person("liujun");
    person.setOfficeAreaCode("86");
    person.setOfficeNumber("13451913228");
    String outTelephone = person.getTelephoneNumber();
    Assert.assertEquals("(86)13451913228", outTelephone);
  }
}
