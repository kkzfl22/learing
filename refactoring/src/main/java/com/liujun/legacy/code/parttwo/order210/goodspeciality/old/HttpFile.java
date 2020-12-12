package com.liujun.legacy.code.parttwo.order210.goodspeciality.old;

import java.util.ArrayList;
import java.util.List;

/**
 * 用于模拟http的相关特性操作
 *
 * @author liujun
 * @version 0.0.1
 */
public class HttpFile {

  public List getKsrsStream(HttpFileCollection files) {
    List outList = new ArrayList();

    for (String name : files.getNames()) {
      HttpPostedFile file = files.getPosted(name);
      if (file.getFileName().endsWith(".ksr") || file.getFileName().endsWith(".txt")) {
        outList.add(file.getInputStream());
      }
    }

    return outList;
  }
}
