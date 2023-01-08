package com.lld.app.util;

public class SumUtil {

    private long start;
    private long end;
    private long counter;

    public SumUtil(long start, long end) {
        this.start = start;
        this.end = end;
        this.counter = 0;
    }

    public void sum() {
        for (long i = start; i <= end; ++i) {
            counter += i;
        }
    }

    public long getCounter() {
        return counter;
    }
}
