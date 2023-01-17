package com.lld.app.util;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class OddEvenPrinterUtility {
    private int start;
    private int end;

    private int currentPointer;

    private Lock lock = new ReentrantLock();
    private Condition mutex = lock.newCondition();

    public OddEvenPrinterUtility(int start, int end) {
        this.start = start;
        this.end = end;
        this.currentPointer = start;
    }

    public void printOdd() throws InterruptedException {
        lock.lock();
        while (currentPointer < end) {
            while (currentPointer % 2 == 0) {
                mutex.await();
            }

            System.out.println(Thread.currentThread().getName() + " -> Odd number -> " + currentPointer);
            currentPointer++;
            mutex.signal();
        }
        lock.unlock();
    }

    public void printEven() throws InterruptedException {
        lock.lock();
        while (currentPointer < end) {
            while (currentPointer % 2 != 0) {
                mutex.await();
            }

            System.out.println(Thread.currentThread().getName() + " -> Even number -> " + currentPointer);
            currentPointer++;
            mutex.signal();
        }
        lock.unlock();
    }
}
