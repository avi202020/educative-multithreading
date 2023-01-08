package com.lld.app.util;

public class BlockingQueue <T> {
    private T[] array;
    private int capacity;
    private int head;
    private int tail;
    private int size;
    private Object lock = new Object();

    public BlockingQueue(int capacity) {
        this.capacity = capacity;
        this.head = this.tail = this.size = 0;
        array = (T[])(new Object[this.capacity]);
    }

    public void enqueue(final T item) throws InterruptedException {

        synchronized (lock) {
            // if size = capacity then suspend the current thread till consumer thread consumes items to clear the queue space
            while (this.size == this.capacity) {
                System.out.println("Producer Thread : " + Thread.currentThread().getName() + " waiting as queue is full");
                lock.wait();
            }

            // Since we enqueue items on basis of tail, check if tail has already reached size, if so bring it back to 0
            if (this.tail == this.capacity) {
                this.tail = 0;
            }

            // add the item to queue
            array[this.tail] = item;
            this.tail++;
            this.size++;

            // notify and wake up the threads which are waiting for producer thread
            lock.notifyAll();
        }
    }

    public T dequeue() throws InterruptedException {

        synchronized (lock) {
            // if size = 0, wait for the producer to produce anything in the queue
            while (this.size == 0) {
                System.out.println("Consumer Thread : " + Thread.currentThread().getName() + " waiting as queue is empty");
                lock.wait();
            }

            // Since dequeue is done based on the head pointer, check if head has reached the capacity, if so then reset it to 0
            if (this.head == this.capacity) {
                this.head = 0;
            }

            // store the item from head that needs to be dequeued
            T item = array[head];
            head++;
            this.size--;

            // Wake up any waiting producer thread to start producing in the queue
            lock.notifyAll();
            return item;
        }
    }
}
