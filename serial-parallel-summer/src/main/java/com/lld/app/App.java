package com.lld.app;

import com.lld.app.util.SumUtil;

/**
 * Hello world!
 *
 */
public class App
{
    private static long MAX_VALUE = Integer.MAX_VALUE;

    public static void calculateSerialSum() {
        SumUtil serialSummer = new SumUtil(0, MAX_VALUE);

        long startTime = System.currentTimeMillis();
        serialSummer.sum();
        System.out.println("Serial Sum : " + serialSummer.getCounter() + "; Time taken : "
                + (System.currentTimeMillis() - startTime));
    }

    public static void calculateSumUsingTwoThreads() {
        long startTime = System.currentTimeMillis();

        SumUtil parallelSummer1 = new SumUtil(0, MAX_VALUE/2);
        SumUtil parallelSummer2 = new SumUtil((MAX_VALUE/2) + 1, MAX_VALUE);

        Thread thread1 = new Thread(() -> {
            parallelSummer1.sum();
        });

        Thread thread2 = new Thread(() -> {
            parallelSummer2.sum();
        });

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        }catch (InterruptedException exp) {
            System.err.println(exp);
        }

        long sum = parallelSummer1.getCounter() + parallelSummer2.getCounter();
        System.out.println("Parallel Sum : " + sum + "; Time taken : " + (System.currentTimeMillis() - startTime));

    }

    public static void main( String[] args )
    {
        calculateSerialSum();
        calculateSumUsingTwoThreads();
    }
}
