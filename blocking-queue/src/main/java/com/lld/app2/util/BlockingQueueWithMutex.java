package com.lld.app2.util;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BlockingQueueWithMutex<T> {
    private T[] array;
    private int capacity;
    private int head;
    private int tail;
    private int size;
    private Lock lock = new ReentrantLock();

    public BlockingQueueWithMutex(int capacity) {
        this.capacity = capacity;
        this.head = this.tail = this.size = 0;
        array = (T[])(new Object[capacity]);
    }

    public void enqueue(T item) throws InterruptedException {
        lock.lock();
        while (size == capacity) {
            System.out.println("Producer Thread : " + Thread.currentThread().getName() + " waiting as queue is full");
            lock.unlock();
            lock.lock();
        }

        if (tail == capacity) {
            tail = 0;
        }

        array[tail] = item;
        tail++;
        size++;

        lock.unlock();
    }

    public T dequeue() throws  InterruptedException {
        lock.lock();
        while(size == 0) {
            System.out.println("Consumer Thread : " + Thread.currentThread().getName() + " waiting as queue is empty");
            lock.unlock();
            lock.lock();
        }

        if (head == capacity) {
            head = 0;
        }

        T item = array[head];
        head++;
        size--;

        lock.unlock();
        return item;
    }
}
