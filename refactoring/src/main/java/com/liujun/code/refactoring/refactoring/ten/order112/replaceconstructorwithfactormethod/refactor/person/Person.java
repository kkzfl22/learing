package com.liujun.code.refactoring.refactoring.ten.order112.replaceconstructorwithfactormethod.refactor.person;

/**
 * @author liujun
 * @version 0.0.1
 */
public abstract class Person {

  abstract boolean isMale();

  abstract char getCode();

  static Person createMale() {
    return new Male();
  }

  static Person createFemale() {
    return new Female();
  }

  public static void main(String[] args) {
    Person kent = new Male();
    //替换
    Person kents1 = Person.createMale();
  }
}
