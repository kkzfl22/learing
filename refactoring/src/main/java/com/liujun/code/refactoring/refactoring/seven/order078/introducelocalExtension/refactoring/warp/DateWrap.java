package com.liujun.code.refactoring.refactoring.seven.order078.introducelocalExtension.refactoring.warp;

import java.util.Date;

/**
 * 时间包装类
 * <p>
 * 如果只是简单的几个函数比较合适，如果是包装全部，有时会有大量的函数进行包装工作。
 *
 * @author liujun
 * @version 0.0.1
 */
public class DateWrap {

    private Date date;

    public DateWrap(long time) {
        this.date = new Date(time);
    }


    public Date nextDay() {
        return new Date(date.getYear(), date.getMonth(), date.getDay() + 1);
    }

}
