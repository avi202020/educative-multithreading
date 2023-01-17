package com.lld.app4.util;

public class CountingSemaphore {
    private int maxPermits;
    private int usedPermits;

    public CountingSemaphore(int maxPermits) {
        this.maxPermits = maxPermits;
        this.usedPermits = 0;
    }

    public CountingSemaphore(int maxPermits, int initialPermits) {
        this.maxPermits = maxPermits;
        this.usedPermits = this.maxPermits - initialPermits;
    }

    public synchronized void acquire() throws InterruptedException {
        while(usedPermits == maxPermits) {
            wait();
        }

        usedPermits++;
        notify();
    }

    public synchronized void release() throws InterruptedException {
        while (usedPermits == 0) {
            wait();
        }

        usedPermits--;
        notify();
    }
}
