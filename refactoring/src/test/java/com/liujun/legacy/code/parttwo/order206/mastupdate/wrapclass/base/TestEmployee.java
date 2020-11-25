package com.liujun.legacy.code.parttwo.order206.mastupdate.wrapclass.base;

import com.liujun.legacy.code.parttwo.order206.mastupdate.wrapclass.common.PayDispatcher;
import org.junit.Test;

/**
 * @author liujun
 * @version 0.0.1
 */
public class TestEmployee {

  private PayDispatcher payInstance = new PayDispatcher();

  private Employee instance = new Employee(payInstance);

  @Test
  public void testRun() {
    instance.pay();
  }
}
