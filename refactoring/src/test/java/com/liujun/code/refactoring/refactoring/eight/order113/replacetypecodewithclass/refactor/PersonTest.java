package com.liujun.code.refactoring.refactoring.eight.order113.replacetypecodewithclass.refactor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;

/**
 * 测试重构后的血液类
 *
 * @author liujun
 * @version 0.0.1
 */
public class PersonTest {

  @Test
  public void testPerson() {
    // 修改引用点，让原引用变成对Person的引用
    Person instance = new Person(BloodGroup.GROUPA);
    assertEquals(instance.getBloodGroup(), BloodGroup.GROUPA);
    Person instanceb = new Person(BloodGroup.GROUPB);
    assertEquals(instanceb.getBloodGroup(), BloodGroup.GROUPB);
    Person instanceo = new Person(BloodGroup.GROUPO);
    assertEquals(instanceo.getBloodGroup(), BloodGroup.GROUPO);
    Person instanceab = new Person(BloodGroup.GROUPAB);
    assertEquals(instanceab.getBloodGroup(), BloodGroup.GROUPAB);
  }
}
