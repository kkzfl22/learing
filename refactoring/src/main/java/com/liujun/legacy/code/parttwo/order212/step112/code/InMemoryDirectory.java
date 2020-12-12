package com.liujun.legacy.code.parttwo.order212.step112.code;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liujun
 * @version 0.0.1
 */
public class InMemoryDirectory {

  private List<Element> elements = new ArrayList<>();

  public void addElement(Element newElement) {
    this.elements.add(newElement);
  }

  public void generateIndex() {
    Element index = new Element("index");
    for (Element current : elements) {
      index.addText(current.getText() + "\n");
    }
    this.addElement(index);
  }

  public int getElementCount() {
    return elements.size();
  }

  public Element getElement(String name) {
    for (Element current : elements) {
      if (current.getName().equals(name)) {
        return current;
      }
    }
    return null;
  }
}
