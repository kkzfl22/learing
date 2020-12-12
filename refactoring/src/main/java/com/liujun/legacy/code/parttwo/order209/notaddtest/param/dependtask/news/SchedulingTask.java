package com.liujun.legacy.code.parttwo.order209.notaddtest.param.dependtask.news;

import com.liujun.legacy.code.parttwo.order209.notaddtest.param.dependtask.base.MeetingResolver;
import com.liujun.legacy.code.parttwo.order209.notaddtest.param.dependtask.base.Scheduler;
import com.liujun.legacy.code.parttwo.order209.notaddtest.param.dependtask.base.SerialTask;

/**
 * @author liujun
 * @version 0.0.1
 */
public class SchedulingTask extends SerialTask implements SerialTaskInf {

  public SchedulingTask(Scheduler scheduler, MeetingResolver resolver) {
    super("", "");
  }

  @Override
  public void run() {}
}
