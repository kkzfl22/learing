package com.liujun.legacy.code.parttwo.order210.goodspeciality.old;

import java.util.List;
import java.util.Map;

import com.liujun.legacy.code.parttwo.order210.goodspeciality.old.HttpFileCollectionInf;
import com.liujun.legacy.code.parttwo.order210.goodspeciality.old.HttpPostedFile;

/**
 * @author liujun
 * @version 0.0.1
 */
public final class HttpFileCollection implements HttpFileCollectionInf {

  private HttpFileCollection() {}

  private List<String> names;

  private Map<String, HttpPostedFile> postFile;

  @Override
  public List<String> getNames() {
    return names;
  }

  @Override
  public HttpPostedFile getPosted(String name) {
    return postFile.get(name);
  }
}
