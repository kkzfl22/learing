package com.liujun.code.refactoring.refactoring.eight.order084.changreferancetovalue.src;

/**
 * 货币种类
 *
 * System.out.println(Currency.get("USD").equals(Currency.get("USD"))) return false
 *
 *
 *
 * @author liujun
 * @version 0.0.1
 */
public class Currency {

  private String code;

  public String getCode() {
    return code;
  }

  private Currency(String code) {
    this.code = code;
  }

  public static Currency get(String type) {
    return new Currency(type);
  }
}
