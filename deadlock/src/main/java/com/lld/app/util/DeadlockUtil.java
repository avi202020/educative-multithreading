package com.lld.app.util;

public class DeadlockUtil {
    private int counter;
    private Object lock1 = new Object();
    private Object lock2 = new Object();

    public void incrementCounter() throws InterruptedException {
        synchronized (lock1) {
            System.out.println(Thread.currentThread().getName() + " Acquired lock1");
            Thread.sleep(100);

            synchronized (lock2) {
                this.counter++;
            }
        }
    }

    public void decrementCounter() throws InterruptedException {
        synchronized (lock1) {
            System.out.println(Thread.currentThread().getName() + " Acquired lock2");
            Thread.sleep(100);

            synchronized (lock2) {
                this.counter--;
            }
        }
    }
}
