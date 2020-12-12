package com.liujun.legacy.code.parttwo.order210.hidemethod.news;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * 起床的流程
 *
 * @author liujun
 * @version 0.0.1
 */
public class TestGetBedOperator {

  private GetBedOperator instance = new GetBedOperator();

  @Test
  @DisplayName("测试穿衣服 ")
  public void testDressClothe() {
    instance.dressClothes();
  }

  @Test
  @DisplayName("测试刷牙")
  public void testBrushDent() {
    instance.brushDent();
  }

  @Test
  @DisplayName("测试出门 ")
  public void testGetOut() {
    instance.getOut();
  }
}
