package com.lld.app3.util;

public class TokenBucketFilterFactory {
    public TokenBucketFilterFactory() {
    }

    public static TokenBucketFilter createTokenBucketFilter(int maxTokens) {
        TokenBucketFilter tokenBucketFilter = new MultithreadedTokenBucketFilter(maxTokens);
        tokenBucketFilter.initialise();
        return tokenBucketFilter;
    }
}
