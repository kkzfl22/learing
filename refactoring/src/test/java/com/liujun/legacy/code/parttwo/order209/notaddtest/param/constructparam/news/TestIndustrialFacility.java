package com.liujun.legacy.code.parttwo.order209.notaddtest.param.constructparam.news;

import org.junit.jupiter.api.Test;

import com.liujun.legacy.code.parttwo.order209.notaddtest.param.constructparam.base.Facility;
import com.liujun.legacy.code.parttwo.order209.notaddtest.param.constructparam.base.IndustrialFacility;
import com.liujun.legacy.code.parttwo.order209.notaddtest.param.constructparam.base.Permit;
import com.liujun.legacy.code.parttwo.order209.notaddtest.param.constructparam.base.PermitViolation;

/**
 * @author liujun
 * @version 0.0.1
 */
public class TestIndustrialFacility {

  class AlwaysValidPermit extends Permit {

    public AlwaysValidPermit(String userName) {
      super(userName);
    }

    @Override
    public void validate() {}
  }

  @Test
  public void testValid() throws PermitViolation {

    Facility instance = new IndustrialFacility(1, "feifei", new AlwaysValidPermit("name"));
    instance.run();
  }
}
