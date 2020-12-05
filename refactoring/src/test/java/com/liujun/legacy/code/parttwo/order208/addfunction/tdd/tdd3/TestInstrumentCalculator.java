package com.liujun.legacy.code.parttwo.order208.addfunction.tdd.tdd3;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 测试二阶矩的方法
 *
 * @author liujun
 * @version 0.0.1
 */
public class TestInstrumentCalculator {

  private static final double TOLERANCE = 0.0;

  @Test
  public void testSecondMoment() throws InvalidBasisException {
    InstrumentCalculator calculator = new InstrumentCalculator();
    calculator.addElement(1.0);
    calculator.addElement(2.0);
    assertEquals(0.5, calculator.secondMomentAbout(2.0), TOLERANCE);
  }
}
