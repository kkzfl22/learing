package com.liujun.legacy.code.parttwo.order209.notaddtest.param.nullpattern.base;

/**
 * 对象工厂
 *
 * @author liujun
 * @version 0.0.1
 */
public class CustomerFactory {

  private static final String[] DATA = {"1", "2", "3"};

  public RealDataQuery query(String query) {

    for (String dataId : DATA) {
      if (dataId.equals(query)) {
        return new RealDataQuery();
      }
    }

    return null;
  }
}
