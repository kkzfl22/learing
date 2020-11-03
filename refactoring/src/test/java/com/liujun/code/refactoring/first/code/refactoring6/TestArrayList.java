package com.liujun.code.refactoring.first.code.refactoring6;

import org.junit.Test;

import java.util.Collections;
import java.util.List;

/**
 * 测试数据集
 *
 * @author liujun
 * @version 0.0.1
 */
public class TestArrayList {

  @Test
  public void dataAssert() {
    List<String> data = Collections.emptyList();
    List<Object> data1 = Collections.emptyList();

    System.out.println(data);
    System.out.println(data1);
  }
}
