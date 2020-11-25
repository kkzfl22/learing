package com.liujun.code.refactoring.refactoring.eleven.order111.replaceinheritancewithdelegation.src;

import java.util.Vector;

/**
 * @author liujun
 * @version 0.0.1
 */
public class MyStack extends Vector {

  public void push(Object element) {
    insertElementAt(element, 0);
  }

  public Object pop() {
    Object result = firstElement();
    removeElement(result);
    return result;
  }
}
