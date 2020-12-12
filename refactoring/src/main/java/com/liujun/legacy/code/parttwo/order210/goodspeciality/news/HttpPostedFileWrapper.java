package com.liujun.legacy.code.parttwo.order210.goodspeciality.news;

import com.liujun.legacy.code.parttwo.order210.goodspeciality.old.HttpPostedFile;
import com.liujun.legacy.code.parttwo.order210.goodspeciality.old.HttpPostedFileInf;

import java.io.InputStream;

/**
 * 通过模拟重写的试，能更好的进行测试
 *
 * @author liujun
 * @version 0.0.1
 */
public class HttpPostedFileWrapper implements HttpPostedFileInf {

  private HttpPostedFile file;

  public HttpPostedFileWrapper(HttpPostedFile file) {
    this.file = file;
  }

  @Override
  public String getFileName() {
    return file.getFileName();
  }

  @Override
  public InputStream getInputStream() {
    return file.getInputStream();
  }
}
