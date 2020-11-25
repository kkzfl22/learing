package com.liujun.code.refactoring.refactoring.ten.order112.replaceconstructorwithfactormethod.refactor.person;

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
