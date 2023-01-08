package com.lld.app3;

import com.lld.app3.util.BlockingQueue;

public class ProducerConsumerWithSemaphore {
    public static void main(String[] args) {
        BlockingQueue<Integer> blockingQueue = new BlockingQueue<>(5);

        Thread producer = new Thread(() -> {
            for (int i=0; i<50; ++i) {
                try {
                    blockingQueue.enqueue(Integer.valueOf(i));
                    System.out.println(Thread.currentThread().getName() + " Produced -> " + i);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Thread consumer1 = new Thread(() -> {
            for (int i=0; i<25; ++i) {
                try {
                    int value = blockingQueue.dequeue().intValue();
                    System.out.println(Thread.currentThread().getName() + " Consumed -> " + value);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Thread consumer2 = new Thread(() -> {
            for (int i=0; i<25; ++i) {
                try {
                    int value = blockingQueue.dequeue().intValue();
                    System.out.println(Thread.currentThread().getName() + " Consumed -> " + value);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        producer.start();
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        consumer1.start();
        consumer2.start();

        try {
            producer.join();
            consumer1.join();
            consumer2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Exiting program");
    }
}
