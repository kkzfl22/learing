package com.liujun.code.refactoring.refactoring.ten.order112.replaceconstructorwithfactormethod.src.person;

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
