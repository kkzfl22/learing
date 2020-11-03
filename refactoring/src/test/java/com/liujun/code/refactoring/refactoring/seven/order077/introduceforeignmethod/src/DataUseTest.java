package com.liujun.code.refactoring.refactoring.seven.order077.introduceforeignmethod.src;

import org.junit.Test;

/**
 * @author liujun
 * @version 0.0.1
 */
public class DataUseTest {

  @Test
  public void dateTest() {
    DataUse data = new DataUse(2020, 9, 29);
    data.useDate();
  }
}
