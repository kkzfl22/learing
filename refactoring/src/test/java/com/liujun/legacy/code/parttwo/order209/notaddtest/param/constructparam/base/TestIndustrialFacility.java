package com.liujun.legacy.code.parttwo.order209.notaddtest.param.constructparam.base;

import org.junit.jupiter.api.Test;

/**
 * @author liujun
 * @version 0.0.1
 */
public class TestIndustrialFacility {

  @Test
  public void testValid() throws PermitViolation {
    Permit permit = new Permit("feifei");

    Facility instance = new IndustrialFacility(1, "feifei", permit);
    instance.run();
  }
}
