package com.liujun.legacy.code.parttwo.order209.notaddtest.param.dependtask.news;

import org.junit.jupiter.api.Test;

/**
 * 测试洋葱的构建
 *
 * @author liujun
 * @version 0.0.1
 */
public class TestSchedulingTask {

  @Test
  public void newCreateNull() {

    SerialTaskInf task = new SchedulingTask(null, null);

    task.run();
  }
}
