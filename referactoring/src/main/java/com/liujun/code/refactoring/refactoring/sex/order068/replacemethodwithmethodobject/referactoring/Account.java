package com.liujun.code.refactoring.refactoring.sex.order068.replacemethodwithmethodobject.referactoring;

/**
 * 进行重构操作
 *
 * 使用Replace Method With Method Object
 *
 * @author liujun
 * @version 0.0.1
 */
public class Account {

    public int gama(int inputVal, int quantity, int yarToDate) {
        return new Gamma(this,inputVal,inputVal,yarToDate).compute();
    }

    public int delta() {
        return 2000;
    }

}
