package com.liujun.code.refactoring.refactoring.nine.order097.intrucenullobject.refactor;

import org.junit.Test;

/**
 * @author liujun
 * @version 0.0.1
 */
public class TestSiteClient {

  @Test
  public void testClient() {
    SiteClient newClient = new SiteClient();
    newClient.doInvoke(new Site());
  }
}
