package com.lld.app2;

import com.lld.app2.util.NonBlockingCounter;

import java.util.HashSet;
import java.util.Set;

public class App2 {
    public static void main(String[] args) throws InterruptedException {
        NonBlockingCounter nonBlockingCounter = new NonBlockingCounter();
        Set<Thread> allThreads = new HashSet<>();
        for (int i=0; i<10; ++i) {
            Thread thread = new Thread(() -> {
                nonBlockingCounter.increment();
            });
            allThreads.add(thread);
        }

        for (Thread thread : allThreads) {
            thread.start();
        }

        for (Thread thread: allThreads) {
            thread.join();
        }

        System.out.println("Final Value :" + nonBlockingCounter.get());

    }
}
