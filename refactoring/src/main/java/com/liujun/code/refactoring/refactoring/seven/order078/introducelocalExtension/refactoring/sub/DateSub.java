package com.liujun.code.refactoring.refactoring.seven.order078.introducelocalExtension.refactoring.sub;

import java.util.Date;

/**
 * 子类化方案实现时间
 *
 * @author liujun
 * @version 0.0.1
 */
public class DateSub extends Date {

    public DateSub(long time) {
        super(time);
    }

    public Date nextDay() {
        return new Date(getYear(), getMonth(), getDate() + 1);
    }

}
