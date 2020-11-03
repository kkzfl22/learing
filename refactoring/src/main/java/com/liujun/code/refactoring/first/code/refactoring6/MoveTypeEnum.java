package com.liujun.code.refactoring.first.code.refactoring6;

/**
 * 影片分类信息
 *
 * @author liujun
 * @version 0.0.1
 */
public enum MoveTypeEnum {

  /** 影片分类，儿童类影片 */
  CHILDREN(2),

  /** 普通影片 */
  REGULAR(0),

  /** 新片 */
  NEW_RELEASE(1);

  private int type;

  MoveTypeEnum(int type) {
    this.type = type;
  }

  public int getType() {
    return type;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("MoveTypeEnum{");
    sb.append("type=").append(type);
    sb.append('}');
    return sb.toString();
  }
}
