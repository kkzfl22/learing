package com.liujun.code.refactoring.first.code.refactoring6;

import com.liujun.code.refactoring.first.code.refactoring6.type.ChildrenMove;
import com.liujun.code.refactoring.first.code.refactoring6.type.NewReleaseMove;
import com.liujun.code.refactoring.first.code.refactoring6.type.RegularMove;

import java.util.HashMap;
import java.util.Map;

/**
 * 影片分类计算
 *
 * @author liujun
 * @version 0.0.1
 */
public class MoveTypeCount {

  /** 影片分类计算信息存储 */
  private static final Map<Integer, MoveTypeCountInf> MOVE_TYPE_MAP = new HashMap<>();

  static {
    // 儿童片的计算
    MOVE_TYPE_MAP.put(MoveTypeEnum.CHILDREN.getType(), new ChildrenMove());
    // 正常影片计算
    MOVE_TYPE_MAP.put(MoveTypeEnum.REGULAR.getType(), new RegularMove());
    // 新影片的计算
    MOVE_TYPE_MAP.put(MoveTypeEnum.NEW_RELEASE.getType(), new NewReleaseMove());
  }

  /**
   * 获取影片分类的计算实体,及其计算过程封装
   *
   * @param typeValue
   * @return
   */
  public static MoveTypeCountInf getCountType(int typeValue) {
    MoveTypeCountInf type = MOVE_TYPE_MAP.get(typeValue);
    if (null != type) {
      return type;
    }
    // 如果
    return MOVE_TYPE_MAP.get(MoveTypeEnum.REGULAR.getType());
  }
}
