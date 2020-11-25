package com.liujun.legacy.code.parttwo.order206.mastupdate.sproutclass.legacycode;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.commons.lang3.RandomStringUtils;

import com.liujun.legacy.code.parttwo.order206.mastupdate.sproutclass.common.Result;

/**
 * @author liujun
 * @version 0.0.1
 */
public class QuarterlyReportGenerator {

  public String generate() {
    List<Result> results = this.queryResults();

    StringBuilder pageText = new StringBuilder();

    pageText.append("<html>");
    pageText.append("<head>");
    pageText.append("<title>").append("Quarterly Report").append("</title>");
    pageText.append("</head>");
    pageText.append("<body>");
    pageText.append("<table>");
    if (results != null && !results.isEmpty()) {

      // 直接在代码中添加，这是传统的代码修改方式
      pageText.append("<tr>");
      pageText.append("<td>").append("Department").append("</td>");
      pageText.append("<td>").append("Manager").append("</td>");
      pageText.append("<td>").append("Profit").append("</td>");
      pageText.append("<td>").append("Expenses").append("</td>");
      pageText.append("<tr>");

      for (Result resultItem : results) {
        pageText.append("<tr>");
        pageText.append("<td>").append(resultItem.getDepartment()).append("</td>");
        pageText.append("<td>").append(resultItem.getManager()).append("</td>");
        pageText.append("<td>").append(resultItem.getNetProfit() / 100).append("</td>");
        pageText.append("<td>").append(resultItem.getOperatingExpense() / 100).append("</td>");
        pageText.append("</tr>");
      }
    } else {
      pageText.append("No Result For this Period");
    }
    pageText.append("</table>");
    pageText.append("</body>");
    pageText.append("</html>");

    return pageText.toString();
  }

  private List<Result> queryResults() {

    int size = ThreadLocalRandom.current().nextInt(2, 20);
    List<Result> dataList = new ArrayList<>(size);

    for (int i = 0; i < size; i++) {
      Result result = new Result();
      result.setDepartment(RandomStringUtils.randomAlphabetic(5));
      result.setManager(RandomStringUtils.randomAlphabetic(5));
      result.setNetProfit(ThreadLocalRandom.current().nextInt());
      result.setOperatingExpense(ThreadLocalRandom.current().nextInt());
      dataList.add(result);
    }

    return dataList;
  }
}
