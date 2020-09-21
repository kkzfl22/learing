package com.liujun.code.refactoring.refactoring.sex.order065.introduceexplainingvariable.src;

/**
 * 引入解释性变量的原始代码
 *
 * @author liujun
 * @version 0.0.1
 */
public class DataDemo {

  public int dataCount() {
    String platform = "macStart";
    String browser = "ie data";
    int resize = 1;

    boolean platformCheck = platform.toUpperCase().indexOf("MAC") > -1;
    boolean browsCheck = browser.toUpperCase().indexOf("IE") > -1;
    boolean resizeCheck = resize > 0;

    if (platformCheck && browsCheck && wasInitialize() && resizeCheck) {
      System.out.println(" print data");
      return 1;
    }

    return -1;
  }

  /** @return */
  private boolean wasInitialize() {
    System.out.println("want count");
    return true;
  }


}
