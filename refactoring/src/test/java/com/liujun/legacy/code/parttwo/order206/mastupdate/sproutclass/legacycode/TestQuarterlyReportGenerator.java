package com.liujun.legacy.code.parttwo.order206.mastupdate.sproutclass.legacycode;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;

/**
 * 单元测试
 *
 * @author liujun
 * @version 0.0.1
 */
public class TestQuarterlyReportGenerator {

  @Test
  public void testGenerate() {
    QuarterlyReportGenerator instance = new QuarterlyReportGenerator();
    String result = instance.generate();
    System.out.println(result);
    assertNotNull(result);
  }
}
