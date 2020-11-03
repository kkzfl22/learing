package com.liujun.code.refactoring.refactoring.eight.order113.replacetypecodewithclass.src;

import org.junit.Assert;
import org.junit.Test;

/**
 * 测试
 *
 * @author liujun
 * @version 0.0.1
 */
public class PersonTest {

  @Test
  public void testPerson() {
    Person instance = new Person(Person.A);
    Assert.assertEquals(instance.getBloodGroup(), Person.A);
    Person instanceb = new Person(Person.B);
    Assert.assertEquals(instanceb.getBloodGroup(), Person.B);
    Person instanceo = new Person(Person.O);
    Assert.assertEquals(instanceo.getBloodGroup(), Person.O);
    Person instanceab = new Person(Person.AB);
    Assert.assertEquals(instanceab.getBloodGroup(), Person.AB);
  }
}
