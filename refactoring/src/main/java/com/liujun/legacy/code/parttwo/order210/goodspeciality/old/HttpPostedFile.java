package com.liujun.legacy.code.parttwo.order210.goodspeciality.old;

import java.io.InputStream;

/**
 * @author liujun
 * @version 0.0.1
 */
public class HttpPostedFile implements HttpPostedFileInf {

  /** 文件名 */
  private String fileName;

  private InputStream inputStream;

  @Override
  public String getFileName() {
    return fileName;
  }

  @Override
  public InputStream getInputStream() {
    return inputStream;
  }
}
