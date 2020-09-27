package com.liujun.code.refactoring.refactoring.sex.order068.replacemethodwithmethodobject.src;

/**
 * 原始代码
 *
 * 一个包含大量局部变量的函数
 *
 * @author liujun
 * @version 0.0.1
 */
public class Account {

  public int gama(int inputVal, int quantity, int yarToDate) {
    int importantValue1 = (inputVal * quantity) + delta();
    int importantValue2 = (inputVal * yarToDate) + 100;
    if (yarToDate - importantValue1 > 1000) {
      importantValue2 -= 20;
    }
    int importantValue3 = importantValue2 * 7;
    return importantValue3 - 2 * importantValue1;
  }

  public int delta() {
    return 2000;
  }
}
