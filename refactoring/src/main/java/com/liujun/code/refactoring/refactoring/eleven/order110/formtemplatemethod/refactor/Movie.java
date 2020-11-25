package com.liujun.code.refactoring.refactoring.eleven.order110.formtemplatemethod.refactor;

/**
 * 影片类
 *
 * @author liujun
 * @version 0.0.1
 */
public class Movie {

  /** 影片分类，儿童类影片 */
  public static final int CHILDRENS = 2;

  /** 普通影片 */
  public static final int REGULAR = 0;

  /** 新片 */
  public static final int NEW_RELEASE = 1;

  /** 标题 */
  private String title;

  /** 价格 */
  private int priceCode;

  public Movie(String title, int priceCode) {
    this.title = title;
    this.priceCode = priceCode;
  }

  public String getTitle() {
    return title;
  }

  public int getPriceCode() {
    return priceCode;
  }

  public void setPriceCode(int priceCode) {
    this.priceCode = priceCode;
  }
}
