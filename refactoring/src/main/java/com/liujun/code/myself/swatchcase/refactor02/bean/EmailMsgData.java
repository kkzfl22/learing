package com.liujun.code.myself.swatchcase.refactor02.bean;

/**
 * 消息发送的类
 *
 * @author liujun
 * @version 0.0.1
 */
public class EmailMsgData extends MsgData {

  /** 主题 */
  private String subject;

  public EmailMsgData(String toId, String subject, String context) {
    super(toId, context);
    this.subject = subject;
  }

  public String getSubject() {
    return subject;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("EmailMsgData{");
    sb.append("subject='").append(subject).append('\'');
    sb.append('}');
    sb.append(super.toString());
    return sb.toString();
  }
}
