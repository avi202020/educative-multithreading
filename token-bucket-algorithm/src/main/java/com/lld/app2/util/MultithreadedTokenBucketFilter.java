package com.lld.app2.util;

public class MultithreadedTokenBucketFilter {
    private final int MAX_TOKEN;
    private final int SLEEP_TIME;
    private int possibleTokens;

    public MultithreadedTokenBucketFilter(int maxToken) {
        this.MAX_TOKEN = maxToken;
        this.SLEEP_TIME = 1000;
        this.possibleTokens = 0;

        Thread tokenFillerDaemon = new Thread(() -> {
            daemonTokenFiller();
        });
        tokenFillerDaemon.setDaemon(true);
        tokenFillerDaemon.start();
    }

    public void getToken() throws InterruptedException {
        synchronized (this) {
            while (possibleTokens == 0) {
                this.wait();
            }
            possibleTokens--;
        }
        System.out.println("Token acquired by :" + Thread.currentThread().getName() + " at " + (System.currentTimeMillis()/1000));
    }

    public void daemonTokenFiller() {
        while (true) {
            synchronized (this) {
                if (possibleTokens < MAX_TOKEN) {
                    possibleTokens++;
                }
                this.notify();
            }

            try {
                Thread.sleep(SLEEP_TIME);
            } catch (InterruptedException e) {
                // swallow exception
//                throw new RuntimeException(e);
            }
        }
    }
}
