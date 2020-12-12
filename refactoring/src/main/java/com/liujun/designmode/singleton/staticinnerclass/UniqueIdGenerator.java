package com.liujun.designmode.singleton.staticinnerclass;

import java.util.UUID;

/**
 * 单例模型工的应用，饿汉式
 *
 * @author liujun
 * @version 0.0.1
 */
public class UniqueIdGenerator {

  private static UniqueIdGenerator INSTANCE;

  /** 私有化构建函数 */
  private UniqueIdGenerator() {}

  /** 通过静态内部类获取实例 */
  private static class SingleInner {
    private static final UniqueIdGenerator INSTANCE = new UniqueIdGenerator();
  }

  public static UniqueIdGenerator getInstance() {
    return SingleInner.INSTANCE;
  }

  public String getUniqueId() {
    return UUID.randomUUID().toString();
  }
}
