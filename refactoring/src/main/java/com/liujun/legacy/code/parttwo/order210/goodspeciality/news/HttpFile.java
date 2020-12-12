package com.liujun.legacy.code.parttwo.order210.goodspeciality.news;

import java.util.ArrayList;
import java.util.List;

import com.liujun.legacy.code.parttwo.order210.goodspeciality.old.HttpFileCollectionInf;
import com.liujun.legacy.code.parttwo.order210.goodspeciality.old.HttpPostedFileInf;

/**
 * 用于模拟http的相关特性操作
 *
 * @author liujun
 * @version 0.0.1
 */
public class HttpFile {

  public List getKsrsStream(HttpFileCollectionInf files) {
    List outList = new ArrayList();

    for (String name : files.getNames()) {
      HttpPostedFileInf file = files.getPosted(name);
      if (file.getFileName().endsWith(".ksr") || file.getFileName().endsWith(".txt")) {
        outList.add(file.getInputStream());
      }
    }

    return outList;
  }
}
