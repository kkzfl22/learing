package com.liujun.code.refactoring.refactoring.eight.order111.encapsulatecollection.src;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * 测试数据的添加操作
 *
 * @author liujun
 * @version 0.0.1
 */
public class CourseTest {

  @Test
  public void operatorTest() {
    Person kent = new Person();
    Set<Course> dataSet = new HashSet<>();
    dataSet.add(new Course("java", false));
    dataSet.add(new Course("C++", false));
    dataSet.add(new Course("c", false));
    dataSet.add(new Course("go", false));
    kent.setCourses(dataSet);
    Assert.assertEquals(4, kent.getCourse().size());

    Course refact = new Course("sql", true);
    kent.getCourse().add(refact);
    kent.getCourse().add(new Course("javascript", false));
    Assert.assertEquals(6, kent.getCourse().size());

    kent.getCourse().remove(refact);
    Assert.assertEquals(5, kent.getCourse().size());
  }
}
