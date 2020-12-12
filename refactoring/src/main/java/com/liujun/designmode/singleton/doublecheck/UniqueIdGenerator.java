package com.liujun.designmode.singleton.doublecheck;

import java.util.UUID;

/**
 * 单例模型工的应用，双重检查
 *
 * @author liujun
 * @version 0.0.1
 */
public class UniqueIdGenerator {

  private static UniqueIdGenerator INSTANCE;

  /** 私有化构建函数 */
  private UniqueIdGenerator() {}

  public static UniqueIdGenerator getInstance() {
    if (null == INSTANCE) {
      // 类级别的锁
      synchronized (UniqueIdGenerator.class) {
        if (null == INSTANCE) {
          INSTANCE = new UniqueIdGenerator();
        }
      }
    }
    return INSTANCE;
  }

  public String getUniqueId() {
    return UUID.randomUUID().toString();
  }
}
