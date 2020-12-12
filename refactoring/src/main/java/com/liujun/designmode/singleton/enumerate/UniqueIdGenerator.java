package com.liujun.designmode.singleton.enumerate;

import java.util.UUID;

/**
 * 单例模型工的应用，枚举式
 *
 * @author liujun
 * @version 0.0.1
 */
public enum UniqueIdGenerator {
  INSTANCE;

  public String getUniqueId() {
    return UUID.randomUUID().toString();
  }
}
