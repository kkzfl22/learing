package com.liujun.code.refactoring.refactoring.eleven.order107.extractsuperclass.refactor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author liujun
 * @version 0.0.1
 */
public class Department extends Party {

  private List<Employee> staff = new ArrayList<>();

  public Department(String name) {
    super(name);
  }

  @Override
  public int getAnnualCost() {
    int result = 0;

    Iterator<Employee> dataIter = this.getStaff();

    while (dataIter.hasNext()) {
      Employee empInfo = dataIter.next();
      result += empInfo.getAnnualCost();
    }

    return result;
  }

  public Iterator<Employee> getStaff() {
    return staff.iterator();
  }

  public void addStaff(Employee arg) {
    staff.add(arg);
  }
}
