package com.liujun.code.myself.swatchcase.refactor03.sender;

/**
 * 消息发送的公共操作
 *
 * @author liujun
 * @version 0.0.1
 */
public class NotifySendUtils {
  /**
   * 给一个邮箱，发送邮件
   *
   * @param funCode 功能类型编码
   * @param sendToMail 地址
   * @param subject 主题
   * @param content 内容
   * @return 返回发送结果
   */
  public static boolean sendMail(
      String funCode, String sendToMail, String subject, String content) {

    return true;
  }

  /**
   * 给单个手机号，发送短信
   *
   * @param funCode 功能类型编码
   * @param sendToMobileNos 接收的号码
   * @param smsContent 短信内容
   */
  public static void sendSms(String funCode, String sendToMobileNos, String smsContent) {
    System.out.println("短信的发送开始.....");
    System.out.println(
        "短信发送: " + ",sendToMobileNos:" + sendToMobileNos + ",smsContent:" + smsContent);
    System.out.println("短信的发送结束.....");
  }

  /**
   * 给单个微信openID，发送模板消息（类似银行交易提醒通知）
   *
   * @param funCode 功能类型编码
   * @param toUserOpenId 发送的目标用户（微信openId)
   * @param context 内容
   */
  public static void sendWxMsg(String funCode, String toUserOpenId, String context) {
    System.out.println("微信消息发送开始.....");
    System.out.println(
        "微信消息:funCode" + funCode + ",toUserOpenId:" + toUserOpenId + "，context:" + context);
    System.out.println("微信消息发送结束.....");
  }

  /**
   * 发送钉钉消息
   *
   * @param funCode 功能类型编码
   * @param toUserOpenId 接收的id
   * @param context 发送的内容
   */
  public static void sendDingDingMsg(String funCode, String toUserOpenId, String context) {
    System.out.println("钉钉消息发送开始.....");
    System.out.println(
        "钉钉消息:funCode" + funCode + ",toUserOpenId:" + toUserOpenId + ",tplData:" + context);
    System.out.println("钉钉消息发送结束.....");
  }

  /**
   * 企业微信消息的发送
   *
   * @param funCode 功能类型编码
   * @param toUserOpenId 接收消息的id
   * @param context 发送的内容
   */
  public static void sendWorkWeChatMsg(String funCode, String toUserOpenId, String context) {
    System.out.println("企业微信消息发送开始.....");
    System.out.println(
        "企业微信消息:funCode" + funCode + ",toUserOpenId:" + toUserOpenId + ",tplData:" + context);
    System.out.println("企业微信消息发送结束.....");
  }
}
