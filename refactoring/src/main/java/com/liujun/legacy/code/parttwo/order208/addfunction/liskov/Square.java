package com.liujun.legacy.code.parttwo.order208.addfunction.liskov;

/**
 * 正方式的子类
 *
 * @author liujun
 * @version 0.0.1
 */
public class Square extends Rectangle {

  public Square(int x, int y, int width) {
    super(x, y, width, width);
  }

  @Override
  public void setWidth(int width) {
    super.setWidth(width);
    super.setHeight(width);
  }

  @Override
  public void setHeight(int height) {
    super.setHeight(height);
    super.setWidth(height);
  }
}
