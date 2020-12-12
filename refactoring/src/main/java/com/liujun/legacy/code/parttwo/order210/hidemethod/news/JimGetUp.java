package com.liujun.legacy.code.parttwo.order210.hidemethod.news;

/**
 * @author liujun
 * @version 0.0.1
 */
public class JimGetUp {

  private GetBedOperator operator = new GetBedOperator();

  public void getUp() {
    System.out.println("起床的流程....");
    this.dressClothes();
    this.brushDent();
    this.getOut();
  }

  /** 穿衣服 */
  private void dressClothes() {
    operator.dressClothes();
  }

  /** 刷新 */
  private void brushDent() {
    operator.brushDent();
  }

  /** 出门 */
  private void getOut() {
    operator.getOut();
  }
}
