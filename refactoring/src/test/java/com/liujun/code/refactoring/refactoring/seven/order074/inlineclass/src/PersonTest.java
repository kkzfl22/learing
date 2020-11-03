package com.liujun.code.refactoring.refactoring.seven.order074.inlineclass.src;

import org.junit.Assert;
import org.junit.Test;

/**
 * 进行测试
 *
 * <p>此种重构会改变引用关系，引用的客户端也会发生改变
 *
 * @author liujun
 * @version 0.0.1
 */
public class PersonTest {

  @Test
  public void testPerson() {
    Person person = new Person("liujun");

    person.getOfficeTelephone().setOfficeAreaCode("86");
    person.getOfficeTelephone().setOfficeNumber("13451913228");

    String outTelephone = person.getTelephoneNumber();
    Assert.assertEquals("(86)13451913228", outTelephone);
  }
}
