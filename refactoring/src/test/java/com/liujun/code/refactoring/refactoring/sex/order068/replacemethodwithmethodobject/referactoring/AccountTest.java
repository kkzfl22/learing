package com.liujun.code.refactoring.refactoring.sex.order068.replacemethodwithmethodobject.referactoring;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.liujun.code.refactoring.refactoring.sex.order068.replacemethodwithmethodobject.src.Account;

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
        assertEquals(296700, rsp);
    }

}
