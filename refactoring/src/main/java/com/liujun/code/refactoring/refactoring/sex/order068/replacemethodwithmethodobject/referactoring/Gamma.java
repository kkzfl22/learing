package com.liujun.code.refactoring.refactoring.sex.order068.replacemethodwithmethodobject.referactoring;

/**
 * 用于保存临时变量及相关对象
 *
 * @author liujun
 * @version 0.0.1
 */
public class Gamma {

  private final Account account;

  private int inputVal;

  private int quantity;

  private int yarToDate;

  private int importantValue1;

  private int importantValue2;

  private int importantValue3;

  public Gamma(Account account, int inputVal, int quantity, int yarToDate) {
    this.account = account;
    this.inputVal = inputVal;
    this.quantity = quantity;
    this.yarToDate = yarToDate;
  }

  public int compute() {
    importantValue1 = (inputVal * quantity) + account.delta();
    importantValue2 = (inputVal * yarToDate) + 100;
    //由于提取临时变量全被提取到类中，克可以任意重构。
    importantThingCount();
    importantValue3 = importantValue2 * 7;
    return importantValue3 - 2 * importantValue1;
  }

  private void importantThingCount() {
    if (yarToDate - importantValue1 > 1000) {
      importantValue2 -= 20;
    }
  }
}
