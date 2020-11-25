package com.liujun.legacy.code.parttwo.order206.mastupdate.sproutmethod.common;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liujun
 * @version 0.0.1
 */
public class TransactionBundle {

  private List<DataOperator> dataList;

  public TransactionBundle(int defSize) {
    this.dataList = new ArrayList<>();
  }

  public static TransactionBundle getInstance() {
    return new TransactionBundle(2);
  }

  public List<DataOperator> getListManager() {
    return dataList;
  }

  public boolean hashEntry(DataOperator operatorInstance) {
    return dataList.contains(operatorInstance);
  }
}
