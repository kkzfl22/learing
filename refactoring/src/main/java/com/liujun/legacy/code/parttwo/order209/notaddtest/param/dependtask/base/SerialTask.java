package com.liujun.legacy.code.parttwo.order209.notaddtest.param.dependtask.base;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author liujun
 * @version 0.0.1
 */
@Getter
@Setter
@ToString
public class SerialTask {

  private String taskName;

  private String dataName;

  public SerialTask(String taskName, String dataName) {
    this.taskName = taskName;
    this.dataName = dataName;
  }

  public void run() {
    System.out.println("SerialTask run");
  }
}
