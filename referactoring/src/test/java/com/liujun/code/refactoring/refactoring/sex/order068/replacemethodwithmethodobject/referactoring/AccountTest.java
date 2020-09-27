package com.liujun.code.refactoring.refactoring.sex.order068.replacemethodwithmethodobject.referactoring;

import com.liujun.code.refactoring.refactoring.sex.order068.replacemethodwithmethodobject.src.Account;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author liujun
 * @version 0.0.1
 */
public class AccountTest {


    @Test
    public void testGama() {
        int inputVal = 1000;
        int quantity = 200;
        int yearToDate = 100;

        Account instance = new Account();
        int rsp = instance.gama(inputVal, quantity, yearToDate);
        System.out.println(rsp);
        Assert.assertEquals(296700, rsp);
    }

}
