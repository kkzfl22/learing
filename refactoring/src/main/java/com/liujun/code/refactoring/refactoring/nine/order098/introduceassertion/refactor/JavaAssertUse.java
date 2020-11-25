package com.liujun.code.refactoring.refactoring.nine.order098.introduceassertion.refactor;

/**
 * java自带的assert关键字，需要在运行时指定jvm 参数 -ea参数来执行，默认不会执行
 *
 * @author liujun
 * @version 0.0.1
 */
public class JavaAssertUse {

  public static void check(boolean value) {
    assert value : "value is error";
    System.out.println(value);
  }

  public static void main(String[] args) {
    JavaAssertUse.check(false);
  }
}
