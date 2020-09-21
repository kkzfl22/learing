package com.liujun.code.refactoring.first.code.refactoring6;

/**
 * 影片类
 *
 * @author liujun
 * @version 0.0.1
 */
public class Movie {

  /** 影片分类，儿童类影片 */
  public static final int CHILDRENS = MoveTypeEnum.CHILDREN.getType();

  /** 普通影片 */
  public static final int REGULAR = MoveTypeEnum.REGULAR.getType();

  /** 新片 */
  public static final int NEW_RELEASE = MoveTypeEnum.NEW_RELEASE.getType();

  /** 标题 */
  private String title;

  /** 票价 */
  private int priceCode;

  /** 影片分类 */
  private MoveTypeCountInf moveType;

  public Movie(String title, int priceCode) {
    this.title = title;
    this.priceCode = priceCode;
    // 获取分类计算实例
    this.moveType = MoveTypeCount.getCountType(this.priceCode);
  }

  public String getTitle() {
    return title;
  }

  public int getPriceCode() {
    return priceCode;
  }

  /**
   * 将计算逻辑分离
   *
   * @return 积分计算
   */
  public double countAmount(int daysRented) {
    return this.moveType.countAmount(daysRented);
  }

  /**
   * 积分计算方法
   *
   * @return 积分
   */
  public int frequentRenterCount(int daysRented) {
    return this.moveType.frequentRenterCount(daysRented);
  }
}
