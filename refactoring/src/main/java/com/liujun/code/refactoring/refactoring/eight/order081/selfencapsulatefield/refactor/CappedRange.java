package com.liujun.code.refactoring.refactoring.eight.order081.selfencapsulatefield.refactor;

/**
 * 当拥有子类后，上面动作的价值就体现出来了，可以很灵活的修改取值
 * @author liujun
 * @version 0.0.1
 */
public class CappedRange extends IntRange {


    public CappedRange(int low, int high, int cap) {
        super(low, high);
        this.cap = cap;
    }

    private int cap;

    public int getCap() {
        return cap;
    }


    @Override
    public int getHigh() {
        return Math.min(super.getHigh(), getCap());
    }
}
