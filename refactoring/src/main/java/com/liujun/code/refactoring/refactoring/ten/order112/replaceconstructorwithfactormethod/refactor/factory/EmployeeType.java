package com.liujun.code.refactoring.refactoring.ten.order112.replaceconstructorwithfactormethod.refactor.factory;

/**
 * @author liujun
 * @version 0.0.1
 */
public abstract class EmployeeType {

  /**
   * 获取类型码
   *
   * @return 返回类型信息
   */
  abstract int getTypeCode();

  static final int ENGINEER = 0;

  static final int SALESMAN = 1;

  static final int MANAGER = 2;

  static final String ENGINEER_TYPE = "Engineer";

  static final String SALESMAN_TYPE = "Saleman";

  static final String MANAGER_TYPE = "Manager";

  /**
   * 添加创建函数
   *
   * @param name
   * @return
   */
  private static EmployeeType create(String name) {
    try {
      return (EmployeeType) Class.forName(name).newInstance();
    } catch (Exception e) {
      throw new IllegalArgumentException("unable to instantiate " + name);
    }
  }

  abstract int payAmount(Employee emp);

  public static void main(String[] args) {
    EmployeeType employee = EmployeeType.create(EmployeeType.ENGINEER_TYPE);
    System.out.println(employee);
  }
}
