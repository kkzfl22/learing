package com.liujun.legacy.code.parttwo.order206.mastupdate.sproutmethod.legacycode;

import java.util.ArrayList;
import java.util.List;

import com.liujun.legacy.code.parttwo.order206.mastupdate.sproutmethod.common.DataOperator;
import com.liujun.legacy.code.parttwo.order206.mastupdate.sproutmethod.common.TransactionBundle;

/**
 * @author liujun
 * @version 0.0.1
 */
public class TransactionGate {

  /** 获取对象信息 */
  private TransactionBundle transactionBundle;

  public TransactionGate(TransactionBundle transactionBundle) {
    this.transactionBundle = transactionBundle;
  }

  public void postEntries(List<DataOperator> entries) {
    List<DataOperator> entryDataList = new ArrayList<>(entries.size());
    for (DataOperator item : entries) {
      // 先检查是否存在,不存在，则添加
      if (!transactionBundle.hashEntry(item)) {
        item.postDate();
        entryDataList.add(item);
      }
    }
    transactionBundle.getListManager().addAll(entryDataList);
  }
}
