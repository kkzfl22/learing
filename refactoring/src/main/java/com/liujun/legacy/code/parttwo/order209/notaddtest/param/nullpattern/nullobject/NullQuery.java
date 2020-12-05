package com.liujun.legacy.code.parttwo.order209.notaddtest.param.nullpattern.nullobject;

/**
 * 数据未找到的情况
 *
 * @author liujun
 * @version 0.0.1
 */
public class NullQuery implements CustomerInf {

  @Override
  public String query() {
    return "can not find";
  }

  @Override
  public boolean isNul() {
    return true;
  }
}
