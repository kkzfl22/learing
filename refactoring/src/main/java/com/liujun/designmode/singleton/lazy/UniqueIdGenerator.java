package com.liujun.designmode.singleton.lazy;

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

  public static synchronized UniqueIdGenerator getInstance() {
    if (INSTANCE == null) {
      INSTANCE = new UniqueIdGenerator();
    }

    return INSTANCE;
  }

  public String getUniqueId() {
    return UUID.randomUUID().toString();
  }
}
