package com.liujun.designmode.singleton.hungry;

import java.util.UUID;

/**
 * 单例模型工的应用，懒汉式
 *
 * @author liujun
 * @version 0.0.1
 */
public class UniqueIdGenerator {

  private static final UniqueIdGenerator INSTANCE = new UniqueIdGenerator();

  /** 私有化构建函数 */
  private UniqueIdGenerator() {}

  public static UniqueIdGenerator getInstance() {
    return INSTANCE;
  }

  public String getUniqueId() {
    return UUID.randomUUID().toString();
  }
}
