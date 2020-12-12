package com.liujun.legacy.code.parttwo.order210.goodspeciality.old;

import java.io.InputStream;

/**
 * 在getKsrsStream方法中使用的是fileName方法，可以提取一个接口
 *
 * <p>进而剥离并外覆API，解开HttpPostedFile之间的耦合
 *
 * @author liujun
 * @version 0.0.1
 */
public interface HttpPostedFileInf {

  /**
   * 提取公用的接口
   *
   * @return 获取文件名
   */
  String getFileName();

  /**
   * 获取输入流
   *
   * @return
   */
  InputStream getInputStream();
}
