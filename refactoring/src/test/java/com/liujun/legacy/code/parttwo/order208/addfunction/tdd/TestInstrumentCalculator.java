package com.liujun.legacy.code.parttwo.order208.addfunction.tdd;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.liujun.legacy.code.parttwo.order208.addfunction.tdd.tdd1.InstrumentCalculator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author liujun
 * @version 0.0.1
 */
public class TestInstrumentCalculator {

  private static final double TOLERANCE = 0.0;

  @Test
  @DisplayName("测试驱动开发操作")
  public void testFirstMoment() {
    InstrumentCalculator calculator = new InstrumentCalculator();
    calculator.addElement(1.0);
    calculator.addElement(2.0);

    assertEquals(-0.5, calculator.firstMomentAbout(2.0), TOLERANCE);
  }


}
