package com.lld.app;

import com.lld.app.util.OptimisticLockCounter;

import java.util.HashSet;
import java.util.Set;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        OptimisticLockCounter lockCounter = new OptimisticLockCounter(0);

        Set<Thread> allThreads = new HashSet<>();
        for (int i=0; i<10; ++i) {
            Thread thread = new Thread(() -> {
                lockCounter.count();
            });
            allThreads.add(thread);
        }

        for (Thread thread : allThreads) {
            thread.start();
        }

        for (Thread thread : allThreads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        System.out.println(lockCounter.geCount());

    }
}
