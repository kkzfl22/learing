package com.liujun.legacy.code.parttwo.order208.addfunction.liskov;

import org.junit.jupiter.api.Test;

/**
 * 测试正方形
 *
 * @author liujun
 * @version 0.0.1
 */
public class TestSquare {

  @Test
  public void testArea() {

    Rectangle r = new Square(0, 1, 10);
    r.setHeight(3);
    r.setWidth(4);

    System.out.println(r.getArea());
  }
}
