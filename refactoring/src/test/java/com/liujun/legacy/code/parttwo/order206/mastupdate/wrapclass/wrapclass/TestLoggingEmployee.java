package com.liujun.legacy.code.parttwo.order206.mastupdate.wrapclass.wrapclass;

import com.liujun.legacy.code.parttwo.order206.mastupdate.wrapclass.base.Employee;
import com.liujun.legacy.code.parttwo.order206.mastupdate.wrapclass.common.PayDispatcher;

import org.junit.jupiter.api.Test;

/**
 * 测试发工资并记录日志的功能
 *
 * @author liujun
 * @version 0.0.1
 */
public class TestLoggingEmployee {

  @Test
  public void logPay() {

    PayDispatcher payDispatcher = new PayDispatcher();
    Employee employee = new Employee(payDispatcher);

    LoggingEmployee instance = new LoggingEmployee(employee);

    instance.pay();
  }
}
