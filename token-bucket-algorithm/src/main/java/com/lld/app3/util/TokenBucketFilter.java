package com.lld.app3.util;

public abstract class TokenBucketFilter {
    public abstract void getToken() throws InterruptedException;
    public abstract void initialise();
}
