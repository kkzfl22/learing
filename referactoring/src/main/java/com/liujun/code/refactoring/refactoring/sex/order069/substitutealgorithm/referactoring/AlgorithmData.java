package com.liujun.code.refactoring.refactoring.sex.order069.substitutealgorithm.referactoring;

import java.util.Arrays;
import java.util.List;

/**
 * 算法替换的原始代码
 *
 * @author liujun
 * @version 0.0.1
 */
public class AlgorithmData {

  public String foundPerson(String[] people) {
    List<String> candidates = Arrays.asList(new String[] {"Don", "John", "Kent"});

    for (int i = 0; i < people.length; i++) {
      if (candidates.contains(people[i])) {
        return people[i];
      }
    }

    return "";
  }
}
