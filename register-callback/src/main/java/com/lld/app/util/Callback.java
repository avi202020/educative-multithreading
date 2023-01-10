package com.lld.app.util;

public class Callback {
    private long executeAt;
    private String message;

    public Callback(long executeAfterSecs, String message) {
        this.executeAt = System.currentTimeMillis() + (executeAfterSecs * 1000);
        this.message = message;
    }

    public long getExecuteAt() {
        return this.executeAt;
    }

    public String getMessage() {
        return this.message;
    }
}
