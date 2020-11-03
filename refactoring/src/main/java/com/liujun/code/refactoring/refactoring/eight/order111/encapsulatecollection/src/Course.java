package com.liujun.code.refactoring.refactoring.eight.order111.encapsulatecollection.src;

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

  private Set<Course> courses;

  public Set<Course> getCourse() {
    return courses;
  }

  public void setCourses(Set<Course> courses) {
    this.courses = courses;
  }
}
