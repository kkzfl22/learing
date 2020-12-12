package com.liujun.legacy.code.parttwo.order212.step112.code;

/**
 * @author liujun
 * @version 0.0.1
 */
public class Element {

  private String name;

  private String text = "";

  public Element(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void addText(String newText) {
    text += newText;
  }

  public String getText() {
    return text;
  }
}
