package com.lld.app;

import com.lld.app.util.BlockingQueue;

/**
 * Hello world!
 *
 */
public class ProducerConsumerWithObjectMonitor
{
    public static void main( String[] args ) throws InterruptedException {
        BlockingQueue<Integer> blockingQueue = new BlockingQueue<>(5);

        Thread producer = new Thread(() -> {
            for (int i=0; i<50; ++i) {
                try {
                    blockingQueue.enqueue(Integer.valueOf(i));
                    System.out.println("Item produced : " + Integer.valueOf(i) + " by Thread : " + Thread.currentThread().getName());
                } catch (InterruptedException e) {

                }
            }
        });

        Thread consumer1 = new Thread(() -> {
            for (int i = 0; i<25; ++i) {
                try {
                    System.out.println("Item dequeued : " + blockingQueue.dequeue() + " By Thread: " + Thread.currentThread().getName());
                } catch (InterruptedException e) {
                }
            }
        });

        Thread consumer2 = new Thread(() -> {
            for (int i = 0; i<25; ++i) {
                try {
                    System.out.println("Item dequeued : " + blockingQueue.dequeue() + " By Thread: " + Thread.currentThread().getName());
                } catch (InterruptedException e) {
                }
            }
        });

        producer.start();
        Thread.sleep(4000);

        consumer1.start();
        consumer2.start();


        try {
            producer.join();
            consumer1.join();
            consumer2.join();
        } catch (InterruptedException e) {
        }
    }
}
