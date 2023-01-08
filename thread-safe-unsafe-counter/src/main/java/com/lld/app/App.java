package com.lld.app;

import com.lld.app.util.ThreadUnsafeCounter;

import java.util.Random;

/**
 * Hello world!
 *
 */
public class App
{
    public static Random random = new Random(System.currentTimeMillis());
    public static void main( String[] args )
    {
        ThreadUnsafeCounter threadUnsafeCounter = new ThreadUnsafeCounter();

        Thread incrementThread = new Thread(() -> {
            for(int i=0; i<100; ++i) {
                threadUnsafeCounter.increment();
                App.sleep();
            }
        });

        Thread decrementThread = new Thread(() -> {
            for(int i=0; i<100; ++i) {
                threadUnsafeCounter.decrement();
                App.sleep();
            }
        });

        incrementThread.start();
        decrementThread.start();


        try {
            incrementThread.join();
            decrementThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        threadUnsafeCounter.printCounter();

    }

    public static void sleep() {
        try {
            Thread.sleep(random.nextInt(10));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
