package com.liujun.legacy.code.parttwo.order208.addfunction.tdd.tdd2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author liujun
 * @version 0.0.1
 */
public class TestInstrumentCalculator {

  @Test
  @DisplayName("大小为0的异常")
  public void testFirstMoment2() {
    InstrumentCalculator calculator = new InstrumentCalculator();
    Exception exception =
        assertThrows(InvalidBasisException.class, () -> calculator.firstMomentAbout(0.0));
    assertEquals(exception.getMessage(), "no elements");
  }
}
