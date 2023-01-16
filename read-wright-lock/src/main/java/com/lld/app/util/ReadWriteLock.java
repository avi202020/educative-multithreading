package com.lld.app.util;

/**
 * 1. Before READ is allowed, there should not be any ongoing write operation.
 * 2. Before WRITE is allowed, ther should not be any ongoing read or write operation.
 */
public class ReadWriteLock {
    int readers = 0;
    boolean isWriteLockAcquired = false;

    /**
     * Don't acquire the read lock until the isWriteLockAcquired is true
     */
    public synchronized void acquireReadLock() throws InterruptedException {
        while (isWriteLockAcquired) {
            wait();
        }
        readers++;
    }

    /**
     * Since acquireWriteLock method depends on the readers value, when the readers value is decreased by 1, there
     * might be a possibility of it being decreasing to 0, so the condition in acquireWriteLock might become false and
     * a thread waiting to get the write lock might get chance to write. So we necessarily need to call the notify
     * method here.
     */
    public synchronized void releaseReadLock() {
        readers--;
        notify();
    }

    /**
     * Before acquiring the write lock, we need to make sure that no thread is writing, and also no thread is reading
     * 1. Check if any other writer has acquired the write lock
     * 2. Check if read lock is acquired
     */
    public synchronized void acquireWriteLock() throws InterruptedException {
        while (isWriteLockAcquired || readers != 0) {
            wait();
        }

        isWriteLockAcquired = true;
    }

    /**
     * Since isWriteLockAcquired is set to false, we need to notify that write lock has been released so that threads
     * waiting to get the write lock can continue
     */
    public synchronized void releaseWriteLock() {
        isWriteLockAcquired = false;
        notify();
    }

}
