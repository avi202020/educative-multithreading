package com.lld.app;

import com.lld.app.util.TokenBucketFilter;

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
        TokenBucketFilter tokenBucketFilter = new TokenBucketFilter(10);
        try {
            System.out.println("Waiting initially for 10 seconds to generate all the tokens");
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Set<Thread> allThreads = new HashSet<>();

        for (int i=0; i<20; ++i) {
            Thread thread = new Thread(() -> {
                try {
                    tokenBucketFilter.getToken();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });

            thread.setName("Thread_" + i);
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
