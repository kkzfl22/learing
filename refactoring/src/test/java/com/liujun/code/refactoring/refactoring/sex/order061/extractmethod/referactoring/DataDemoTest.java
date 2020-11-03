package com.liujun.code.refactoring.refactoring.sex.order061.extractmethod.referactoring;

import org.junit.Test;

/**
 * 对函数执行测试
 *
 * @author liujun
 * @version 0.0.1
 */
public class DataDemoTest {

  @Test
  public void printOwing() {
    DataDemo instance = new DataDemo("test");
    // 放入两个测试对象
    instance.add(new Order());
    instance.add(new Order());
    instance.printOwing();
  }
}
