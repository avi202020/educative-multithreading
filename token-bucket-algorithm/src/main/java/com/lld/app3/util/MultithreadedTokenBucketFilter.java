package com.lld.app3.util;

public class MultithreadedTokenBucketFilter extends TokenBucketFilter {
    private final int MAX_TOKENS;
    private final int SLEEP_TIME_IN_MS;
    private int possibleTokens;

    public MultithreadedTokenBucketFilter(int maxTokens) {
        this.MAX_TOKENS = maxTokens;
        this.SLEEP_TIME_IN_MS = 1000;
    }

    @Override
    public void getToken() throws InterruptedException {
        synchronized (this) {
            while(possibleTokens == 0) {
                this.wait();
            }
            possibleTokens--;
        }
        System.out.println(Thread.currentThread().getName() + " acquired token at : " + (System.currentTimeMillis()/1000));
    }

    @Override
    public void initialise() {
        Thread deamonTokenFillerThread = new Thread(() -> {
            daemonTokenFiller();
        });
        deamonTokenFillerThread.setDaemon(true);
        deamonTokenFillerThread.start();
    }

    public void daemonTokenFiller() {
        while (true) {
            synchronized (this) {
                if (possibleTokens < MAX_TOKENS) {
                    possibleTokens++;
                }
                this.notify();
            }

            try {
                Thread.sleep(SLEEP_TIME_IN_MS);
            } catch (InterruptedException e) {
                // swallow exception
            }
        }
    }
}
