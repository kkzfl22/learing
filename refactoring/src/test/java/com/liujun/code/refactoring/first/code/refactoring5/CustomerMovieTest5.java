package com.liujun.code.refactoring.first.code.refactoring5;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;

/**
 * 测试用户租用影片
 *
 * <p>重构的第一步，需要构建可靠的测试环境
 *
 * <p>重构：搬移方法
 *
 * @author liujun
 * @version 0.0.1
 */
public class CustomerMovieTest5 {

  @Test
  public void testRentalMovieNormal() {
    // 1,创建影片
    Movie children = new Movie("训龙高手", Movie.CHILDRENS);
    // 普通片
    Movie normal = new Movie("阿凡达", Movie.REGULAR);
    // 新片
    Movie newMove = new Movie("八百", Movie.NEW_RELEASE);

    // 创建一个顾客
    Customer customer = new Customer("liujun");

    // 顾客租影片
    Rental renInfo = new Rental(normal, 5);
    customer.addRental(renInfo);

    // 输出信息
    String outMsg = customer.statement();
    assertNotNull(outMsg);

    String outEqualValue =
        "Rental Record for liujun\n"
            + "\t阿凡达\t6.5\n"
            + " Amount owed is 6.5\n"
            + " You earned 1 frequent renter points";
    assertEquals(outEqualValue, outMsg);
  }

  @Test
  public void testRentalMovieChildren() {
    // 1,创建影片
    Movie children = new Movie("训龙高手", Movie.CHILDRENS);
    // 普通片
    Movie normal = new Movie("阿凡达", Movie.REGULAR);
    // 新片
    Movie newMove = new Movie("八百", Movie.NEW_RELEASE);

    // 创建一个顾客
    Customer customer = new Customer("liujun");

    // 顾客租影片
    Rental renInfo = new Rental(children, 10);
    customer.addRental(renInfo);

    // 输出信息
    String outMsg = customer.statement();
    String outvalue =
        "Rental Record for liujun\n"
            + "\t训龙高手\t12.0\n"
            + " Amount owed is 12.0\n"
            + " You earned 1 frequent renter points";

    assertNotNull(outMsg);
    assertEquals(outMsg, outvalue);
  }

  @Test
  public void testRentalMovieNewMove() {
    // 1,创建影片
    Movie children = new Movie("训龙高手", Movie.CHILDRENS);
    // 普通片
    Movie normal = new Movie("阿凡达", Movie.REGULAR);
    // 新片
    Movie newMove = new Movie("八佰", Movie.NEW_RELEASE);

    // 创建一个顾客
    Customer customer = new Customer("liujun");

    // 顾客租影片
    Rental renInfo = new Rental(newMove, 10);
    customer.addRental(renInfo);

    String outEqualValue =
        "Rental Record for liujun\n"
            + "\t八佰\t30.0\n"
            + " Amount owed is 30.0\n"
            + " You earned 2 frequent renter points";

    // 输出信息
    String outMsg = customer.statement();
    assertNotNull(outMsg);
    assertEquals(outEqualValue, outMsg);
  }
}
