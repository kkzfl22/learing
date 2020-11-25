package com.liujun.code.refactoring.refactoring.ten.order114.replaceerrorcodewithexception.refactor;

/**
 * @author liujun
 * @version 0.0.1
 */
public class Assert {

  static void isTrue(String comment, boolean test) {
    if (!test) {
      throw new IllegalArgumentException("Assertion failed :" + comment);
    }
  }
}
