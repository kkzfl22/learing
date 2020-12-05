package com.liujun.legacy.code.parttwo.order209.notaddtest.param.base;

/**
 * 计费系统
 *
 * @author liujun
 * @version 0.0.1
 */
public class CreditValidator {

  private RGHConnectionInf connection;

  private CreditMaster master;

  private String validatorId;

  /**
   * 测试计费系统
   *
   * @param connection
   * @param master
   * @param validatorId
   */
  public CreditValidator(RGHConnectionInf connection, CreditMaster master, String validatorId) {
    this.connection = connection;
    this.master = master;
    this.validatorId = validatorId;
  }

  /**
   * 异常信息
   *
   * @param customer
   * @return
   * @throws InvalidCredit
   */
  Certificate validateCustomer(String customer) throws InvalidCredit {

    connection.connect();

    System.out.println(customer);

    connection.disconnect();

    return new Certificate(customer);
  }
}
