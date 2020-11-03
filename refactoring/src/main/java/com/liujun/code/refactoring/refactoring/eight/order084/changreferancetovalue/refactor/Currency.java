package com.liujun.code.refactoring.refactoring.eight.order084.changreferancetovalue.refactor;

/**
 * 货币种类
 *
 * <p>将引用对象变为不可变的值对象，如果不能变为不可变的值对象，此项重构放弃。
 *
 * <p>经验此项重构之后Currency.get("USD").equals(Currency.get("USD"))将返回true
 *
 * <p>还可以将构建函数改为public,去掉工厂方法
 *
 * @author liujun
 * @version 0.0.1
 */
public class Currency {

  private String code;

  public String getCode() {
    return code;
  }

  /**
   * 改靠为公共函数
   *
   * @param code
   */
  public Currency(String code) {
    this.code = code;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    if (!(o instanceof Currency)) {
      return false;
    }
    Currency currency = (Currency) o;
    return this.code.equals(currency.getCode());
  }

  @Override
  public int hashCode() {
    return code.hashCode();
  }
}
