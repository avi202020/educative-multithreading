package com.lld.app;

import com.lld.app.util.ReadWriteLock;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        ReadWriteLock readWriteLock = new ReadWriteLock();

        Thread thread = new Thread(() -> {
            try {
                System.out.println("attempting to acquire write lock " + Thread.currentThread().getName());
                readWriteLock.acquireWriteLock();
                System.out.println("Write lock acquired by " + Thread.currentThread().getName());
                while (true) {
                    Thread.sleep(500);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread thread2 = new Thread(() -> {
            try {
                System.out.println("attempting to acquire write lock " + Thread.currentThread().getName());
                readWriteLock.acquireWriteLock();
                System.out.println("Write lock acquired by " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread tReader1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    readWriteLock.acquireReadLock();
                    System.out.println("Read lock acquired: " + System.currentTimeMillis());

                } catch (InterruptedException ie) {

                }
            }
        });

        Thread tReader2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Read lock about to release: " + System.currentTimeMillis());
                readWriteLock.releaseReadLock();
                System.out.println("Read lock released: " + System.currentTimeMillis());
            }
        });

        try {
            tReader1.start();
            thread.start();
            Thread.sleep(3000);
            tReader2.start();
            Thread.sleep(1000);
            thread2.start();
            tReader1.join();
            tReader2.join();
            thread2.join();
        } catch (InterruptedException exp) {

        }
    }
}
