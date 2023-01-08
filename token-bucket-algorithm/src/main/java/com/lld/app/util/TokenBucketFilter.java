package com.lld.app.util;

public class TokenBucketFilter {
    private final int MAX_TOKENS;
    private int possibleTokens;
    private long lastRequestTime;

    public TokenBucketFilter(int tokenCapacity) {
        this.MAX_TOKENS = tokenCapacity;
        this.lastRequestTime = System.currentTimeMillis();
        this.possibleTokens = 0;
    }

    public synchronized void getToken() throws InterruptedException {
        possibleTokens += ((System.currentTimeMillis() - lastRequestTime) / 1000);

        // if possibleTokens > MAX_TOKENS; set possibleTokens = MAX_TOKENS
        if (possibleTokens > MAX_TOKENS) {
            possibleTokens = MAX_TOKENS;
        }

        // if possibleTokens = 0; sleep current thread for 1 sec to generate new token
        if (possibleTokens == 0) {
            System.out.println("No tokens available, waiting for 1 second");
            Thread.sleep(1000);
        } else {
            possibleTokens--;
        }

        this.lastRequestTime = System.currentTimeMillis();

        System.out.println("Token acquired by : " + Thread.currentThread().getName() + " at : " + System.currentTimeMillis()/1000);
    }
}
