package com.liujun.code.refactoring.refactoring.eight.order116.replacesubclasswithfields.src;

/**
 * @author liujun
 * @version 0.0.1
 */
public class Male extends Person {

  @Override
  boolean isMale() {
    return true;
  }

  @Override
  char getCode() {
    return 'M';
  }
}
