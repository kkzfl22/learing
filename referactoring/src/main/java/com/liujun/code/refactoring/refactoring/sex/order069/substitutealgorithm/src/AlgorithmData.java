package com.liujun.code.refactoring.refactoring.sex.order069.substitutealgorithm.src;

/**
 * 算法替换的原始代码
 *
 * @author liujun
 * @version 0.0.1
 */
public class AlgorithmData {

  public String foundPerson(String[] people) {
    for (int i = 0; i < people.length; i++) {
      if (people[i].equals("Don")) {
        return "Don";
      }
      if (people[i].equals("John")) {
        return "John";
      }
      if (people[i].equals("Kent")) {
        return "Kent";
      }
    }
    return "";
  }
}
