package com.liujun.code.refactoring.refactoring.ten.order109.introdeuceparameterobject.refactor;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * 帐目
 *
 * @author liujun
 * @version 0.0.1
 */
public class Account {

  private List<Entry> dataList = new ArrayList<>();

  public double getFlowBetween(DataRange range) {
    double result = 0;

    Iterator<Entry> iterList = dataList.iterator();

    while (iterList.hasNext()) {
      Entry item = iterList.next();
      if (range.include(item.getChargeDate())) {
        result += item.getValue();
      }
    }

    return result;
  }
}
