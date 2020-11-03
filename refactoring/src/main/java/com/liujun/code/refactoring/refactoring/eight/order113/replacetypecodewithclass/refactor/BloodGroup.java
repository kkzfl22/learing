package com.liujun.code.refactoring.refactoring.eight.order113.replacetypecodewithclass.refactor;

/**
 * 新建立一个类，用于表示血液
 *
 * @author liujun
 * @version 0.0.1
 */
public class BloodGroup {

  public static final BloodGroup GROUPO = new BloodGroup(0);

  public static final BloodGroup GROUPA = new BloodGroup(1);

  public static final BloodGroup GROUPB = new BloodGroup(2);

  public static final BloodGroup GROUPAB = new BloodGroup(3);

  private static final BloodGroup[] GROUPS = new BloodGroup[] {GROUPO, GROUPA, GROUPB, GROUPAB};

  private final int code;

  public static BloodGroup code(int index) {
    return GROUPS[index];
  }

  private BloodGroup(int code) {
    this.code = code;
  }

  public int getCode() {
    return code;
  }
}
