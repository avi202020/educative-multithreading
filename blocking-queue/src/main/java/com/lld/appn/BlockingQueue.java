package com.lld.appn;

public class BlockingQueue<T> {
    T[] array;
    int size;
    int capacity;
    int head;
    int tail;

    CountingSemaphore lock;
    CountingSemaphore semProducers;
    CountingSemaphore semConsumer;

    public BlockingQueue(int capacity) {
        this.array = (T[])(new Object[capacity]);
        this.size = this.head = this.tail = 0;
        this.lock = new CountingSemaphore(1, 1);
        this.semProducers = new CountingSemaphore(capacity, capacity);
        this.semConsumer = new CountingSemaphore(capacity, 0);
    }

    public void enqueue(T item) throws InterruptedException {
        semProducers.acquire();
        lock.acquire();

        if (tail == capacity) {
            tail = 0;
        }

        array[tail++] = item;
        this.size++;

        lock.release();
        semConsumer.release();
    }

    public T dequeue() throws InterruptedException {
        semConsumer.acquire();
        lock.acquire();

        if (head == capacity) {
            head = 0;
        }

        T item = array[head];
        array[head] = null;
        head++;
        size--;

        lock.release();
        semProducers.release();

        return item;
    }

}
