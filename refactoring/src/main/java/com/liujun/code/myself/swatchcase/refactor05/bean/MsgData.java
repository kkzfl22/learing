package com.liujun.code.myself.swatchcase.refactor05.bean;

/**
 * 消息发送的类
 *
 * @author liujun
 * @version 0.0.1
 */
public class MsgData {

  /** 接收的id */
  private String toId;

  /** 发送的消息的内容 */
  private String context;

  public MsgData(String toId, String context) {
    this.toId = toId;
    this.context = context;
  }

  public String getToId() {
    return toId;
  }

  public void setToId(String toId) {
    this.toId = toId;
  }

  public String getContext() {
    return context;
  }

  public void setContext(String context) {
    this.context = context;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("MsgData{");
    sb.append("toId='").append(toId).append('\'');
    sb.append(", context='").append(context).append('\'');
    sb.append('}');
    return sb.toString();
  }
}
