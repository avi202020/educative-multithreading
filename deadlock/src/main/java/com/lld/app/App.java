package com.lld.app;

import com.lld.app.util.DeadlockUtil;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        DeadlockUtil deadlockUtil = new DeadlockUtil();
        Thread incrementThread = new Thread(() -> {
            for (int i=0; i<100; ++i) {
                try {
                    deadlockUtil.incrementCounter();
                    System.out.println("incrementing i: " + i);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Thread decrementThread = new Thread(() -> {
            for (int i=0; i<100; ++i) {
                try {
                    deadlockUtil.decrementCounter();
                    System.out.println("incrementing i: " + i);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
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
    }
}
