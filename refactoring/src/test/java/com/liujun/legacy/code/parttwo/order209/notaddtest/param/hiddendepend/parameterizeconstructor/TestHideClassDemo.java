package com.liujun.legacy.code.parttwo.order209.notaddtest.param.hiddendepend.parameterizeconstructor;

import org.junit.jupiter.api.Test;

/**
 * 测试依赖
 *
 * @author liujun
 * @version 0.0.1
 */
public class TestHideClassDemo {

  @Test
  public void testInvokeBase() {
    HideClassDemo instance = new HideClassDemo();
    instance.invoke();
  }

  @Test
  public void testInvoke() {
    DependClass depend = new TestDependency();
    HideClassDemo instance = new HideClassDemo(depend);
    instance.invoke();
  }

  class TestDependency extends DependClass {

    @Override
    public void dependMethod() {
      System.out.println("依赖的解除，模拟的调用");
    }
  }
}
