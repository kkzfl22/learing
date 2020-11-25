package com.liujun.legacy.code.parttwo.order206.mastupdate.sproutmethod.base;

import com.liujun.legacy.code.parttwo.order206.mastupdate.sproutmethod.common.DataOperator;
import com.liujun.legacy.code.parttwo.order206.mastupdate.sproutmethod.common.TransactionBundle;

import java.util.List;

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
    for (DataOperator item : entries) {
      item.postDate();
    }
    transactionBundle.getListManager().addAll(entries);
  }
}
