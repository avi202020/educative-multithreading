package com.lld.app4.util;

public class BlockingQueue<T> {
    private T[] array;
    private int size;
    private int capacity;
    private int head;
    private int tail;
    private CountingSemaphore semaphoreMutexLock;
    private CountingSemaphore semaphoreProducer;
    private CountingSemaphore semaphoreConsumer;

    public BlockingQueue(int capacity) {
        this.capacity = capacity;
        this.array = (T[])(new Object[this.capacity]);
        this.size = this.head = this.tail = 0;
        this.semaphoreMutexLock = new CountingSemaphore(1, 1);
        this.semaphoreProducer = new CountingSemaphore(capacity, capacity);
        this.semaphoreConsumer = new CountingSemaphore(capacity, 0);
    }

    public void enqueue(T item) throws InterruptedException {
        semaphoreProducer.acquire();
        semaphoreMutexLock.acquire();

        if (tail == capacity) {
            tail = 0;
        }

        array[tail] = item;
        tail++;
        size++;

        semaphoreMutexLock.release();
        semaphoreConsumer.release();
    }

    public T dequeue() throws InterruptedException {
        semaphoreConsumer.acquire();
        semaphoreMutexLock.acquire();

        if (head == capacity) {
            head = 0;
        }

        T item = array[head];
        array[head] = null;
        head++;
        size--;

        semaphoreMutexLock.release();
        semaphoreProducer.release();

        return item;
    }
}
