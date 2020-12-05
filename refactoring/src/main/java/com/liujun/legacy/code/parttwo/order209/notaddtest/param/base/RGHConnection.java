package com.liujun.legacy.code.parttwo.order209.notaddtest.param.base;

import java.io.IOException;

/**
 * 连接信息
 *
 * @author liujun
 * @version 0.0.1
 */
public class RGHConnection implements RGHConnectionInf {

  /** 端口 */
  private int port;

  /** 用户名 */
  private String name;

  /** 密码 */
  private String passwd;

  public RGHConnection(int port, String name, String passwd) throws IOException {
    this.port = port;
    this.name = name;
    this.passwd = passwd;
  }

  @Override
  public void connect() {
    // 模拟物理连接
    try {
      Thread.sleep(3000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void disconnect() {
    // 模拟物理关闭
    try {
      Thread.sleep(1500);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  
}
