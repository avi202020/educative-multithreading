package com.lld.app2;

import java.util.concurrent.Semaphore;

public class SemaphoreExampleApp {

    public static void main(String[] args) {
//        badSemaphoreAcquireAndRelease();
        goodSemaphoreAcquireAndRelease();
    }

    public static void goodSemaphoreAcquireAndRelease() {
        final Semaphore semaphore = new Semaphore(1);

        Thread badThread = new Thread(() -> {
            while(true) {
                try {
                    semaphore.acquire();
                    try {
                        throw new RuntimeException("Runtime Exception");
                    } catch (Exception exp) {
                        // handle the program logic exception and exit the function
                        System.out.println("Runtime exception handled");
                        return;
                    } finally {
                        System.out.println("Bad thread releasing semaphore");
                        semaphore.release();
                    }

                    // Control never reaches to this point as there is some exception caught
                    // semaphore.release()
                } catch (InterruptedException exp) {

                }
            }
        });

        Thread goodThread = new Thread(() -> {
            System.out.println("Good Thread waiting for the signal to acquire the semaphore");
            try {
                semaphore.acquire();
            } catch (InterruptedException exp) {

            }
        });

        badThread.start();
        goodThread.start();

        try {
            badThread.join();
            goodThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Exiting program");
    }

    public static void badSemaphoreAcquireAndRelease() {
        final Semaphore semaphore = new Semaphore(1);

        Thread badThread = new Thread(() -> {
            while(true) {
                try {
                    semaphore.acquire();
                } catch (InterruptedException exp) {

                }

                throw new RuntimeException("Runtime Exception");

                // Control never reaches to this point as there is some exception caught
                // semaphore.release()
            }
        });

        Thread goodThread = new Thread(() -> {
            System.out.println("Good Thread waiting for the signal to acquire the semaphore");
            try {
                semaphore.acquire();
            } catch (InterruptedException exp) {

            }
        });

        badThread.start();
        goodThread.start();

        try {
            badThread.join();
            goodThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Exiting program");
    }
}
