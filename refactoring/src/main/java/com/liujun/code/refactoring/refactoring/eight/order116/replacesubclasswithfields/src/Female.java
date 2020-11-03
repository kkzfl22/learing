package com.liujun.code.refactoring.refactoring.eight.order116.replacesubclasswithfields.src;

/**
 * @author liujun
 * @version 0.0.1
 */
public class Female extends Person {

  @Override
  boolean isMale() {
    return false;
  }

  @Override
  char getCode() {
    return 'F';
  }
}
