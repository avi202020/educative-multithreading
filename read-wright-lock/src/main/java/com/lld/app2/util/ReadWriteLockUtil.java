package com.lld.app2.util;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockUtil {
    ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    Lock readLock = readWriteLock.readLock();
    Lock writeLock = readWriteLock.writeLock();

    List<Integer> sharedResource = new ArrayList<>();

    public void write(int item) {
        writeLock.lock();
        try {
            sharedResource.add(item);
            System.out.println("Item " + item + " added by " + Thread.currentThread().getName());
        } finally {
            writeLock.unlock();
        }
    }

    public int read(int i) {
        readLock.lock();
        try {
            return sharedResource.get(i);
        } finally {
            readLock.unlock();
        }
    }
}
