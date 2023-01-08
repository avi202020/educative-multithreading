package com.lld.app2;

import com.lld.app2.util.MultithreadedTokenBucketFilter;

import java.util.HashSet;
import java.util.Set;

public class App2 {

    public static void main(String[] args) {
        MultithreadedTokenBucketFilter tokenBucketFilter = new MultithreadedTokenBucketFilter(1);

        Set<Thread> allThreads = new HashSet<>();
        for (int i=0; i<10; ++i) {
            Thread thread = new Thread(() -> {
                try {
                    tokenBucketFilter.getToken();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
            thread.setName("THREAD_" + i);
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

        System.out.println("Exiting program");
    }
}
