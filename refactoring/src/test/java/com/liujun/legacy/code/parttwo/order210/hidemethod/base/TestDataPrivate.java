package com.liujun.legacy.code.parttwo.order210.hidemethod.base;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * 测试私有方法
 *
 * @author liujun
 * @version 0.0.1
 */
public class TestDataPrivate {

  @Test
  @DisplayName("针对私有方法的测试")
  public void testInvoke() {
      JimGetUp jim = new JimGetUp();

      //起床的操作，
      jim.getUp();
  }
}
