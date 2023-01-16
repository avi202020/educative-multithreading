package com.lld.app.util;

import java.util.concurrent.atomic.AtomicBoolean;

public class CompareAndSwapLock {
    private AtomicBoolean atomicBoolean = new AtomicBoolean(false);

    public void unlock() {
        atomicBoolean.set(false);
    }

    public void lock() {
        /**
         * In case the lock is not locked, (is false) -> then we swap the value to true and compareAndSet function returns true and loops terminates
         * In case the value is already set to true -> that means the lock is held by another thread -> then compareAndSet() will return false -> loop keeps on spinning in he while loop
         */
        while (!atomicBoolean.compareAndSet(false, true)) {
            // busy wait - until compareAndSet() succeeds
        }
    }
}
