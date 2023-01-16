package com.lld.app.util;

import java.util.concurrent.atomic.AtomicLong;

public class OptimisticLockCounter {
    private AtomicLong counter;

    public OptimisticLockCounter (long counter) {
        this.counter = new AtomicLong(counter);
    }

    public void count() {
        boolean incSuccessful = false;
        while (!incSuccessful) {
            long value = counter.get();
            long newValue = value + 1;

            incSuccessful = counter.compareAndSet(value, newValue);
        }
    }

    public long geCount() {
        return counter.get();
    }
}
