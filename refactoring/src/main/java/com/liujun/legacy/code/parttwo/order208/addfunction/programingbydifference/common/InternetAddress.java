package com.liujun.legacy.code.parttwo.order208.addfunction.programingbydifference.common;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author liujun
 * @version 0.0.1
 */
@Getter
@Setter
@ToString
public class InternetAddress {

  private String fromAddress;

  public InternetAddress(String fromAddress) {
    this.fromAddress = fromAddress;
  }
}
