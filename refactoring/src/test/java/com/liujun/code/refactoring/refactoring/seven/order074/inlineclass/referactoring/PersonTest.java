package com.liujun.code.refactoring.refactoring.seven.order074.inlineclass.referactoring;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

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

    person.setOfficeAreaCode("86");
    person.setOfficeNumber("13451913228");

    assertEquals("(86)13451913228", person.getTelephoneNumber());
  }
}
