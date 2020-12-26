package com.liujun.code.myself.swatchcase.refactor06.bean;

/**
 * 带功能编码的消息
 *
 * @author liujun
 * @version 0.0.1
 */
public class FunCodeMsgData extends MsgData {

  /** 功能类型编码 */
  private String funCode;

  public FunCodeMsgData(String toId, String context, String funCode) {
    super(toId, context);
    this.funCode = funCode;
  }

  public String getFunCode() {
    return funCode;
  }

  public void setFunCode(String funCode) {
    this.funCode = funCode;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("FunCodeMsgData{");
    sb.append("funCode='").append(funCode).append('\'');
    sb.append('}');
    sb.append(super.toString());
    return sb.toString();
  }
}
