package com.liujun.code.refactoring.refactoring.sex.order065.introduceexplainingvariable.referactoring;

/**
 * 数据计算
 *
 * @author liujun
 * @version 0.0.1
 */
public class DataDemo {


    public int dataCount() {
        String platform = "macStart";
        String browser = "ie data";
        int resize = 1;

        if (platform.toUpperCase().indexOf("MAC") > -1
                && browser.toUpperCase().indexOf("IE") > -1
                && wasInitialize()
                && resize > 0) {
            System.out.println(" print data");
            return 1;
        }

        return -1;
    }

    /** @return */
    private boolean wasInitialize() {
        System.out.println("want count");
        return true;
    }


}
