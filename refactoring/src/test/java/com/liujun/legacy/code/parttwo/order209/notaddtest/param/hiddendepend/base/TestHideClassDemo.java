package com.liujun.legacy.code.parttwo.order209.notaddtest.param.hiddendepend.base;

import com.liujun.legacy.code.parttwo.order209.notaddtest.param.hiddendepend.base.HideClassDemo;
import org.junit.jupiter.api.Test;

/**
 * 测试隐藏依赖的实现
 *
 * @author liujun
 * @version 0.0.1
 */
public class TestHideClassDemo {

  @Test
  public void testInvoke() {
    HideClassDemo instance = new HideClassDemo();
    instance.invoke();
  }
}
