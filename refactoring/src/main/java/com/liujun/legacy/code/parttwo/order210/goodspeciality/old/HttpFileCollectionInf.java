package com.liujun.legacy.code.parttwo.order210.goodspeciality.old;

import java.util.List;

/**
 * http的文件集合接口
 *
 * @author liujun
 * @version 0.0.1
 */
public interface HttpFileCollectionInf {

  /**
   * 获取文件名
   *
   * @return
   */
  List<String> getNames();

  /**
   * 使用名称获取信息
   *
   * @param name
   * @return
   */
  HttpPostedFile getPosted(String name);
}
