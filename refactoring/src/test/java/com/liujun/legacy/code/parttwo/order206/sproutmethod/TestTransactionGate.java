package com.liujun.legacy.code.parttwo.order206.sproutmethod;

import java.util.ArrayList;
import java.util.List;

import com.liujun.legacy.code.parttwo.order206.mastupdate.sproutmethod.newcode.TransactionGate;

import org.junit.jupiter.api.Test;

import com.liujun.legacy.code.parttwo.order206.mastupdate.sproutmethod.common.DataOperator;
import com.liujun.legacy.code.parttwo.order206.mastupdate.sproutmethod.common.TransactionBundle;

/**
 * 测试
 *
 * @author liujun
 * @version 0.0.1
 */
public class TestTransactionGate {

  @Test
  public void testPostEntries() {
    List<DataOperator> entries = new ArrayList<>();
    entries.add(new DataOperator());

    TransactionBundle bundle = TransactionBundle.getInstance();

    TransactionGate instance = new TransactionGate(bundle);
    instance.postEntries(entries);
  }
}
