package com.liujun.code.refactoring.refactoring.ten.order115.replaceexceptionwithtest.refactor;

import java.util.Stack;

import javax.annotation.Resource;

/**
 * 资源池
 *
 * @author liujun
 * @version 0.0.1
 */
public class ResourcePool {

  private Stack<Resource> availableList = new Stack<>();
  private Stack<Resource> allocatedList = new Stack<>();

  public Resource getResource() {
    Resource result;

    // 添加测试资源为空的情况
    if (availableList.isEmpty()) {
      result = new DataResourceDemo();
    } else {
      result = availableList.pop();
    }

    allocatedList.push(result);
    return result;
  }
}
