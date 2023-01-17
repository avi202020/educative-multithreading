package com.lld.app3.util;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BlockingQueueWithConditionVariable<T> {
    private T[] arr;
    private int head;
    private int tail;
    private int size;
    private int capacity;
    private Lock lock = new ReentrantLock();
    private Condition mutex = lock.newCondition();

    public BlockingQueueWithConditionVariable(int capacity) {
        this.capacity = capacity;
        this.arr = (T[]) new Object[capacity];
        this.head = this.tail = this.size = 0;
    }

    public void enqueue(T item) throws InterruptedException {
        lock.lock();
        while (this.size == capacity) {
            System.out.println(Thread.currentThread().getName() + " waiting to produce");
            mutex.await();
        }

        if (tail == capacity) {
            tail = 0;
        }

        arr[tail] = item;
        tail++;
        size++;

        System.out.println(Thread.currentThread().getName() + " produced " + item);
        mutex.signal();
        lock.unlock();
    }

    public T dequeue() throws InterruptedException {
        lock.lock();
        while (this.size == 0) {
            System.out.println(Thread.currentThread().getName() + " waiting to consume");
            mutex.await();
        }

        if (head == capacity) {
            head = 0;
        }

        T item = arr[head];
        arr[head] = null;
        head++;
        size--;

        System.out.println(Thread.currentThread().getName() + " consumed " + item);

        mutex.signal();
        lock.unlock();
        return item;
    }
}
