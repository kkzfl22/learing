package com.liujun.legacy.code.parttwo.order206.mastupdate.wrapmethod.base;

import com.liujun.legacy.code.parttwo.order206.mastupdate.wrapmethod.common.PayDispatcher;
import org.junit.Test;

/**
 * 没说员工
 *
 * @author liujun
 * @version 0.0.1
 */
public class TestEmployee {

  @Test
  public void pay() {
    PayDispatcher payDispatcher = new PayDispatcher();
    Employee instance = new Employee(payDispatcher);

    instance.pay();
  }
}
