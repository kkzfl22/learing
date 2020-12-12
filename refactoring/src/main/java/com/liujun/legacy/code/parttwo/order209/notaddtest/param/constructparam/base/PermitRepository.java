package com.liujun.legacy.code.parttwo.order209.notaddtest.param.constructparam.base;

import java.util.HashMap;
import java.util.Map;

/**
 * @author liujun
 * @version 0.0.1
 */
public class PermitRepository {

  private Map<String, Permit> DATA_MAP = new HashMap<>();

  public static PermitRepository GetInstance() {
    return new PermitRepository();
  }

  public Permit findAssociatedFromOrigination(Permit per) {
    if (DATA_MAP.containsKey(per.getUserName())) {
      return DATA_MAP.get(per.getUserName());
    } else {
      DATA_MAP.put(per.getUserName(), per);
      return per;
    }
  }
}
