package com.liujun.legacy.code.parttwo.order206.mastupdate.sproutclass.sproutclass;

/**
 * @author liujun
 * @version 0.0.1
 */
public class QuarterlyReportTableGenerator implements QuarterlyReportTableHeaderGenerator {

  @Override
  public String generate() {
    StringBuilder outHeader = new StringBuilder();
    outHeader.append("<tr>");
    outHeader.append("<td>").append("Department").append("</td>");
    outHeader.append("<td>").append("Manager").append("</td>");
    outHeader.append("<td>").append("Profit").append("</td>");
    outHeader.append("<td>").append("Expenses").append("</td>");
    outHeader.append("<tr>");
    return outHeader.toString();
  }
}
