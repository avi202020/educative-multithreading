package com.lld.app3;

import com.lld.app3.util.BlockingQueueWithConditionVariable;

public class ProducerConsumerWithConditionVariable {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueueWithConditionVariable<Integer> blockingQueue = new BlockingQueueWithConditionVariable<>(5);

        Thread producer1 = new Thread(() -> {
            for (int i=0; i<50; ++i) {
                try {
                    blockingQueue.enqueue(Integer.valueOf(i));
                } catch (InterruptedException e) {

                }
            }
        });

        Thread producer2 = new Thread(() -> {
            for (int i=50; i<100; ++i) {
                try {
                    blockingQueue.enqueue(Integer.valueOf(i));
                } catch (InterruptedException e) {

                }
            }
        });

        Thread consumer1 = new Thread(() -> {
            for (int i = 0; i<50; ++i) {
                try {
                    blockingQueue.dequeue();
                } catch (InterruptedException e) {
                }
            }
        });

        Thread consumer2 = new Thread(() -> {
            for (int i = 0; i<50; ++i) {
                try {
                    blockingQueue.dequeue();
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
