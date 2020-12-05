package com.liujun.legacy.code.parttwo.order206.legacycode;

import java.util.ArrayList;
import java.util.List;



import com.liujun.legacy.code.parttwo.order206.mastupdate.sproutmethod.common.DataOperator;
import com.liujun.legacy.code.parttwo.order206.mastupdate.sproutmethod.common.TransactionBundle;
import com.liujun.legacy.code.parttwo.order206.mastupdate.sproutmethod.legacycode.TransactionGate;
import org.junit.jupiter.api.Test;

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
