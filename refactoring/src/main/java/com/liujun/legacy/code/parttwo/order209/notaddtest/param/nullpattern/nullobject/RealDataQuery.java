package com.liujun.legacy.code.parttwo.order209.notaddtest.param.nullpattern.nullobject;

/**
 * 用来模拟实际的查询操作
 *
 * @version 0.0.1
 */
public class RealDataQuery implements CustomerInf {

  @Override
  public String query() {
    return "success";
  }

  @Override
  public boolean isNul() {
    return false;
  }
}
