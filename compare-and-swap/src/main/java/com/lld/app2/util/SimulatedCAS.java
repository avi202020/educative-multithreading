package com.lld.app2.util;

public class SimulatedCAS {
    private long value = 0;

    public SimulatedCAS(int value) {
        this.value = value;
    }

    public long getValue() {
        return this.value;
    }
    /**
     * compareAndSwap checks if the expectedValue is equal to the current value,
     * if expectedValue == currentValue => return expectedValue ==> this indicates that compareAndSwap operation is success
     * if expectedValue != currentValue => return currentValue ==> this means compareAndSwap operation was not successful
     */
    synchronized long compareAndSwap(long expectedValue, long newValue) {
        System.out.println(Thread.currentThread().getName() + " attemptings to increment " + expectedValue);
        if (value == expectedValue) {
            value = newValue;
            return expectedValue;
        }
        return value;
    }

    synchronized boolean compareAndSet(long expectedValue, long newValue) {
        return compareAndSwap(expectedValue, newValue) == expectedValue;
    }


}
