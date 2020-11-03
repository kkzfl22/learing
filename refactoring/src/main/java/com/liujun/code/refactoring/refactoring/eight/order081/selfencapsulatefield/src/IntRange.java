package com.liujun.code.refactoring.refactoring.eight.order081.selfencapsulatefield.src;

/**
 * @author liujun
 * @version 0.0.1
 */
public class IntRange {

    private int low, high;

    boolean includes(int arg) {
        return arg >= low && arg <= high;
    }

    void grow(int factor) {
        high = high * factor;
    }

    IntRange(int low, int high) {
        this.low = low;
        this.high = high;
    }
}
