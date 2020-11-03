package com.liujun.code.refactoring.refactoring.eight.order111.encapsulatecollection.refactor;

/**
 * 将使用数组的代码进行封装
 *
 * @author liujun
 * @version 0.0.1
 */
public class DataArray {

  private String[] dataArray;

  public void setData(int index, String value) {
    dataArray[index] = value;
  }

  public void setAll(String[] dataSrc) {
    if (dataSrc.length == 0) {
      return;
    }

    dataArray = new String[dataSrc.length];
    System.arraycopy(dataSrc, 0, dataArray, 0, dataSrc.length);
  }

  public String[] getDataAll() {
    String[] result = new String[dataArray.length];
    System.arraycopy(dataArray, 0, result, 0, result.length);
    return result;
  }
}
