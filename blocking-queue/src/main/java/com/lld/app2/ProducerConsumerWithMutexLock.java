package com.lld.app2;

import com.lld.app2.util.BlockingQueueWithMutex;

public class ProducerConsumerWithMutexLock {
    public static void main( String[] args ) throws InterruptedException {
        BlockingQueueWithMutex<Integer> blockingQueue = new BlockingQueueWithMutex<>(5);

        Thread producer1 = new Thread(() -> {
            for (int i=0; i<50; ++i) {
                try {
                    blockingQueue.enqueue(Integer.valueOf(i));
                    System.out.println("Item produced : " + Integer.valueOf(i) + " by Thread : " + Thread.currentThread().getName());
                } catch (InterruptedException e) {

                }
            }
        });

        Thread producer2 = new Thread(() -> {
            for (int i=50; i<100; ++i) {
                try {
                    blockingQueue.enqueue(Integer.valueOf(i));
                    System.out.println("Item produced : " + Integer.valueOf(i) + " by Thread : " + Thread.currentThread().getName());
                } catch (InterruptedException e) {

                }
            }
        });

        Thread consumer1 = new Thread(() -> {
            for (int i = 0; i<50; ++i) {
                try {
                    System.out.println("Item dequeued : " + blockingQueue.dequeue() + " By Thread: " + Thread.currentThread().getName());
                } catch (InterruptedException e) {
                }
            }
        });

        Thread consumer2 = new Thread(() -> {
            for (int i = 0; i<50; ++i) {
                try {
                    System.out.println("Item dequeued : " + blockingQueue.dequeue() + " By Thread: " + Thread.currentThread().getName());
                } catch (InterruptedException e) {
                }
            }
        });

        producer1.start();
        producer2.start();
        Thread.sleep(4000);

        consumer1.start();
        consumer2.start();

        try {
            producer1.join();
            producer2.join();
            consumer1.join();
            consumer2.join();
        } catch (InterruptedException e) {
        }
    }
}
