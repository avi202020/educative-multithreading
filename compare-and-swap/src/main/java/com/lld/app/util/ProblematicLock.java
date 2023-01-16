package com.lld.app.util;

public class ProblematicLock {
    private volatile boolean locked = false;

    public void unlock() {
        this.locked = false;
    }

    public void lock() {
        while (this.locked == true) {
            // busy wait - until this.locked = false
        }

        this.locked = true;
    }
}
