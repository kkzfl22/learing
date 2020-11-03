package com.liujun.code.refactoring.refactoring.eight.order116.replacesubclasswithfields.refactor;

/**
 * @author liujun
 * @version 0.0.1
 */
public class Person {

  /**
   * 针对每个常量在超类中声明一个对应字段
   *
   * @param isMale
   * @param code
   */
  private final boolean isMale;

  private final char code;

  /**
   * 为超类加一个个protected构建函数
   *
   * @param isMale
   * @param code
   */
  protected Person(boolean isMale, char code) {
    this.isMale = isMale;
    this.code = code;
  }

  /**
   * 在超类中加入常量函数
   *
   * @return
   */
  boolean isMale() {
    return isMale;
  }

  char getCode() {
    return code;
  }

  /**
   * 首先为每个子类建立工厂函数
   *
   * @return
   */
  static Person createMale() {
    return new Person(true, 'M');
  }

  static Person createFemale() {
    return new Person(false, 'F');
  }
}
