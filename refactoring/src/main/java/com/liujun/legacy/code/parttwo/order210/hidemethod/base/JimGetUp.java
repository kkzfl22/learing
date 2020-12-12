package com.liujun.legacy.code.parttwo.order210.hidemethod.base;

/**
 * @author liujun
 * @version 0.0.1
 */
public class JimGetUp {

  public void getUp() {
    System.out.println("起床的流程....");
    this.dressClothes();
    this.brushDent();
    this.getOut();
  }

  /** 穿衣服 */
  private void dressClothes() {
    System.out.println("首先进行穿衣服操作");
  }

  /** 刷新 */
  private void brushDent() {
    System.out.println("刷牙。。");
  }

  /** 出门 */
  private void getOut() {
    System.out.println("出门");
  }
}
