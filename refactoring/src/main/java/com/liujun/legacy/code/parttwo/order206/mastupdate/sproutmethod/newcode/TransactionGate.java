package com.liujun.legacy.code.parttwo.order206.mastupdate.sproutmethod.newcode;

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
    List<DataOperator> entriesToAdd = this.uniqueEntries(entries);
    for (DataOperator item : entriesToAdd) {
      item.postDate();
    }
    transactionBundle.getListManager().addAll(entriesToAdd);
  }

  /**
   * 新生方法，将能独立出来的代码独立出来，不是直接修改修改，
   *
   * @param entries
   * @return
   */
  public List<DataOperator> uniqueEntries(List<DataOperator> entries) {
    List<DataOperator> result = new ArrayList<>(entries.size());
    for (DataOperator item : entries) {
      if (transactionBundle.hashEntry(item)) {
        result.add(item);
      }
    }

    return result;
  }
}
