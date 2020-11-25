package com.liujun.code.refactoring.refactoring.ten.order115.replaceexceptionwithtest.src;

import java.util.EmptyStackException;
import java.util.Stack;

import javax.annotation.Resource;

/**
 * 资源池
 *
 * @author liujun
 * @version 0.0.1
 */
public class ResourcePool {

  private Stack<Resource> available = new Stack<>();
  private Stack<Resource> allocatedList = new Stack<>();

  public Resource getResource() {
    Resource result;

    try {
      result = available.pop();
      allocatedList.push(result);
      return result;
    } catch (EmptyStackException e) {
      result = new DataResourceDemo();
      allocatedList.push(result);
      return result;
    }
  }
}
