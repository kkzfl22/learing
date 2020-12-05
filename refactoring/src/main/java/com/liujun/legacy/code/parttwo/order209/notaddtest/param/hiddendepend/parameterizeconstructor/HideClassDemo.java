package com.liujun.legacy.code.parttwo.order209.notaddtest.param.hiddendepend.parameterizeconstructor;

/**
 * 隐藏依赖的示例
 *
 * @author liujun
 * @version 0.0.1
 */
public class HideClassDemo {

  private final DependClass depend;

  /** 默认的构建函数 */
  public HideClassDemo() {
    depend = new DependClass();
    init();
  }

  /**
   * 新加一个构建函数，为解依赖而设
   *
   * @param depend 依赖的类
   */
  public HideClassDemo(DependClass depend) {
    this.depend = depend;
    init();
  }

  /** 进行相关依赖的初始化操作 */
  private void init() {
    depend.dependMethod();
  }

  public void invoke() {
    System.out.println("方法调用。。。");
  }
}
