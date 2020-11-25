package com.liujun.code.refactoring.refactoring.ten.order115.replaceexceptionwithtest.src;

import javax.annotation.Resource;
import java.lang.annotation.Annotation;

/**
 * @author liujun
 * @version 0.0.1
 */
public class DataResourceDemo implements Resource {

  @Override
  public String name() {
    return null;
  }

  @Override
  public String lookup() {
    return null;
  }

  @Override
  public Class<?> type() {
    return null;
  }

  @Override
  public AuthenticationType authenticationType() {
    return null;
  }

  @Override
  public boolean shareable() {
    return false;
  }

  @Override
  public String mappedName() {
    return null;
  }

  @Override
  public String description() {
    return null;
  }

  @Override
  public Class<? extends Annotation> annotationType() {
    return null;
  }
}
