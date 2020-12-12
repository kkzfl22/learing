package com.liujun.legacy.code.parttwo.order210.goodspeciality.news;

import java.io.InputStream;

import com.liujun.legacy.code.parttwo.order210.goodspeciality.old.HttpPostedFileInf;

/**
 * 通过模拟重写的试，能更好的进行测试
 *
 * @author liujun
 * @version 0.0.1
 */
public class FakeHttpPostedFile implements HttpPostedFileInf {

  /** 文件名 */
  private String fileName;

  /** 流 */
  private InputStream inputStream;

  public FakeHttpPostedFile(String fileName, InputStream inputStream) {
    this.fileName = fileName;
    this.inputStream = inputStream;
  }

  @Override
  public String getFileName() {
    return fileName;
  }

  @Override
  public InputStream getInputStream() {
    return inputStream;
  }
}
