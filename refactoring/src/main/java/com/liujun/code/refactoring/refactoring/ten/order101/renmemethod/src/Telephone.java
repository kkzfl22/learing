package com.liujun.code.refactoring.refactoring.ten.order101.renmemethod.src;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author liujun
 * @version 0.0.1
 */
public class Telephone {

  private String officeAreaCode = String.valueOf(ThreadLocalRandom.current().nextLong());

  private String officeNumber = String.valueOf(ThreadLocalRandom.current().nextLong());;

  public String getTelephoneNumber() {
    return ("(" + officeAreaCode + ")") + officeNumber;
  }
}
