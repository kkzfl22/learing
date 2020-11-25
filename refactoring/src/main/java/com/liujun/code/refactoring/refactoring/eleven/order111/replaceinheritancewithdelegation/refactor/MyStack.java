package com.liujun.code.refactoring.refactoring.eleven.order111.replaceinheritancewithdelegation.refactor;

import java.util.Vector;

/**
 * @author liujun
 * @version 0.0.1
 */
public class MyStack {

  private Vector vector = new Vector();

  public void push(Object element) {
    vector.insertElementAt(element, 0);
  }

  public Object pop() {
    Object result = vector.firstElement();
    vector.removeElement(result);
    return result;
  }

  public int size() {
    return this.vector.size();
  }

  public boolean isEmpty() {
    return vector.isEmpty();
  }
}
