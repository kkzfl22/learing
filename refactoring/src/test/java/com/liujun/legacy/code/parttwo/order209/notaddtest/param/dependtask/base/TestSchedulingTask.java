package com.liujun.legacy.code.parttwo.order209.notaddtest.param.dependtask.base;

import org.junit.jupiter.api.Test;

/**
 * 测试洋葱的构建
 *
 * @author liujun
 * @version 0.0.1
 */
public class TestSchedulingTask {

  @Test
  public void newCreate() {

    Scheduler scheduler = new Scheduler();
    MeetingResolver resolver = new MeetingResolver("", "");
    SchedulingTask task = new SchedulingTask(scheduler, resolver);

    task.run();
  }


  @Test
  public void newCreateNull() {

    Scheduler scheduler = new Scheduler();
    MeetingResolver resolver = new MeetingResolver("", "");
    SchedulingTask task = new SchedulingTask(scheduler, resolver);

    task.run();
  }

}
