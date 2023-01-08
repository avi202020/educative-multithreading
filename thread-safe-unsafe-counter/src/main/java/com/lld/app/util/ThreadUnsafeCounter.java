package com.lld.app.util;

public class ThreadUnsafeCounter {

    private int counter;

    public ThreadUnsafeCounter() {
        this.counter = 0;
    }

    public void increment() {
        this.counter += 1;
    }

    public void decrement() {
        this.counter -= 1;
    }

    public void printCounter() {
        System.out.println("Counter : " + this.counter);
    }
}
