package com.liujun.legacy.code.parttwo.order209.notaddtest.param.hiddendepend.base;

/**
 * @author liujun
 * @version 0.0.1
 */
public class DependClass {

  /** 用于模拟依赖的方法 */
  public void dependMethod() {
    try {
      System.out.println("隐藏依赖调用...");
      Thread.sleep(3002);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
