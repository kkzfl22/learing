package com.liujun.code.refactoring.refactoring.eight.order111.encapsulatecollection.refactor;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * 个人与课程
 *
 * @author liujun
 * @version 0.0.1
 */
public class Course {

  private String name;

  private boolean isAdvanced;

  public Course(String name, boolean isAdvanced) {
    this.name = name;
    this.isAdvanced = isAdvanced;
  }
}

class Person {

  /** 重构第一步，建立合适的取值，设值函数 如果厚在需以以其他方式来初始化集合的，需要做初始化操作 */
  private final Set<Course> courses;

  public Person(Set<Course> courses) {
    this.courses = courses;
  }

  public void addCourse(Course itemInfo) {
    courses.add(itemInfo);
  }

  public void removeCourse(Course itemInfo) {
    courses.remove(itemInfo);
  }

  /**
   * 如果需要返回集合的中所有元素信息.
   *
   * <p>可以让取值函数只返回一个只读的副本，用以确保没有任何一个用户能够通过取值函数修改集合。
   *
   * @return
   */
  public Set<Course> getCourses() {
    return Collections.unmodifiableSet(courses);
  }
}
