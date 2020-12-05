package com.liujun.legacy.code.parttwo.order208.addfunction.liskov;

/**
 * 置换原则示例
 *
 * @author liujun
 * @version 0.0.1
 */
public class Rectangle {

  private int x;

  private int y;

  private int width;

  private int height;

  public Rectangle(int x, int y, int width, int height) {
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
  }

  public void setWidth(int width) {
    this.width = width;
  }

  public void setHeight(int height) {
    this.height = height;
  }

  public int getArea() {
    return width * height;
  }
}
