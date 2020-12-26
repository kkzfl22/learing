package com.liujun.code.myself.swatchcase.src.common;

/**
 * 发送消息的内容
 *
 * @author liujun
 * @version 0.0.1
 */
public class MessageData {

  /** 功能类型编码 */
  private String funCode;

  /** 通知对象（邮箱，手机号，钉钉号） */
  private String notifyNumber;

  /** 主题 */
  private String title;

  /** 短信内容 */
  private String content;

  /** 发送状态（1：未发送 | 2：发送完成 | 3：发送失败） */
  private Integer status;


  public String getNotifyNumber() {
    return notifyNumber;
  }

  public void setNotifyNumber(String notifyNumber) {
    this.notifyNumber = notifyNumber;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public String getFunCode() {
    return funCode;
  }

  public void setFunCode(String funCode) {
    this.funCode = funCode;
  }


  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("MessageData{");
    sb.append("funCode='").append(funCode).append('\'');
    sb.append(", notifyNumber='").append(notifyNumber).append('\'');
    sb.append(", title='").append(title).append('\'');
    sb.append(", content='").append(content).append('\'');
    sb.append(", status=").append(status);
    sb.append('}');
    return sb.toString();
  }
}
