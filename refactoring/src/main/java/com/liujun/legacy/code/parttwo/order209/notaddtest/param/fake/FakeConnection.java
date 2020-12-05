package com.liujun.legacy.code.parttwo.order209.notaddtest.param.fake;

import com.liujun.legacy.code.parttwo.order209.notaddtest.param.base.RGHConnectionInf;

/**
 * 轻量级的测试
 *
 * @author liujun
 * @version 0.0.1
 */
public class FakeConnection implements RGHConnectionInf {

  @Override
  public void connect() {
    System.out.println("测试连接");
  }

  @Override
  public void disconnect() {
    System.out.println("断开连接 ");
  }
}
