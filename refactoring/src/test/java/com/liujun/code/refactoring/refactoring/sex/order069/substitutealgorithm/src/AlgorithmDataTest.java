package com.liujun.code.refactoring.refactoring.sex.order069.substitutealgorithm.src;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

/**
 * 进行原算法的测试
 *
 * @author liujun
 * @version 0.0.1
 */
public class AlgorithmDataTest {

  @Test
  public void testFoundPerson() {
    String[] data = new String[] {"Don"};
    AlgorithmData instance = new AlgorithmData();
    String rsp = instance.foundPerson(data);
    Assert.assertThat(data, Matchers.arrayContaining(rsp));
  }
}
