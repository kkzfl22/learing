package com.liujun.legacy.code.parttwo.order206.mastupdate.wrapclass.wrapclass;

import com.liujun.legacy.code.parttwo.order206.mastupdate.wrapclass.base.Employee;

/**
 * 员工信息
 *
 * @author liujun
 * @version 0.0.1
 */
public class LoggingEmployee extends Employee {

  private Employee employee;

  public LoggingEmployee(Employee employee) {
    super(employee.getPayDispatcher());
    this.employee = employee;
  }

  @Override
  public void pay() {
    this.logPayment();
    employee.pay();
  }

  private void logPayment() {
    System.out.println("记录日志的功能");
  }
}
