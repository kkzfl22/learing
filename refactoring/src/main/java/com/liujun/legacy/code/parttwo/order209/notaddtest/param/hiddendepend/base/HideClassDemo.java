package com.liujun.legacy.code.parttwo.order209.notaddtest.param.hiddendepend.base;

/**
 * 隐藏依赖的示例
 *
 * @author liujun
 * @version 0.0.1
 */
public class HideClassDemo {

  private final DependClass depend;

  public HideClassDemo() {
    depend = new DependClass();
    depend.dependMethod();
  }

  public void invoke() {
    System.out.println("方法调用。。。");
  }
}
