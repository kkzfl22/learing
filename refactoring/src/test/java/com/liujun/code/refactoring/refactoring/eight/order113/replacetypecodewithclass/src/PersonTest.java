package com.liujun.code.refactoring.refactoring.eight.order113.replacetypecodewithclass.src;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;

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
    assertEquals(instance.getBloodGroup(), Person.A);
    Person instanceb = new Person(Person.B);
    assertEquals(instanceb.getBloodGroup(), Person.B);
    Person instanceo = new Person(Person.O);
    assertEquals(instanceo.getBloodGroup(), Person.O);
    Person instanceab = new Person(Person.AB);
    assertEquals(instanceab.getBloodGroup(), Person.AB);
  }
}
