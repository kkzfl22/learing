package com.liujun.legacy.code.parttwo.order206.mastupdate.wrapclass.base;

import com.liujun.legacy.code.parttwo.order206.mastupdate.wrapclass.common.Memory;
import com.liujun.legacy.code.parttwo.order206.mastupdate.wrapclass.common.PayDispatcher;
import com.liujun.legacy.code.parttwo.order206.mastupdate.wrapclass.common.TimeCard;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 员工信息
 *
 * @author liujun
 * @version 0.0.1
 */
public class Employee {

  private PayDispatcher payDispatcher;

  private List<Long> payCard = new ArrayList<>();

  public Employee(PayDispatcher payDispatcher) {
    this.payDispatcher = payDispatcher;
  }

  /** 执行工资的计算操作 */
  public void pay() {
    long date = this.getDate();
    Memory amount = new Memory();
    for (TimeCard timeCard : getTimeCards()) {
      if (payCard.contains(date)) {
        amount.add(timeCard.getHours() * date);
      }
    }

    payDispatcher.pay(date, amount);
  }

  private long getDate() {
    return System.currentTimeMillis();
  }

  public List<TimeCard> getTimeCards() {
    List<TimeCard> dataList = new ArrayList<>();

    for (int i = 0; i < 10; i++) {
      TimeCard timeItem = new TimeCard();
      timeItem.setHours(ThreadLocalRandom.current().nextInt());
      dataList.add(timeItem);
    }

    return dataList;
  }

  public PayDispatcher getPayDispatcher() {
    return payDispatcher;
  }
}
