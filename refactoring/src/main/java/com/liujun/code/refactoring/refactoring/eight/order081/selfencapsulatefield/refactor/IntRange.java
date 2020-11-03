package com.liujun.code.refactoring.refactoring.eight.order081.selfencapsulatefield.refactor;

/**
 * @author liujun
 * @version 0.0.1
 */
public class IntRange {

    private int low, high;

    boolean includes(int arg) {
        return arg >= getLow() && arg <= getHigh();
    }

    void grow(int factor) {
        setHigh(getHigh() * factor);
    }

    IntRange(int low, int high) {
        this.initialize(low, high);
    }

    private void initialize(int low, int high) {
        this.low = low;
        this.high = high;
    }

    public int getLow() {
        return low;
    }

    public void setLow(int low) {
        this.low = low;
    }

    public int getHigh() {
        return high;
    }

    public void setHigh(int high) {
        this.high = high;
    }
}
