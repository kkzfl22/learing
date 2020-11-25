package com.liujun.code.refactoring.refactoring.ten.order106.replaceparameterwithexplicitmethods.src;

/**
 * @author liujun
 * @version 0.0.1
 */
public class DataClient {
  void dataCreate() {
    Employee engineer = Employee.create(Employee.ENGINEER);
    Employee manager = Employee.create(Employee.MANAGER);
    Employee salesman = Employee.create(Employee.SALESMAN);
  }
}
