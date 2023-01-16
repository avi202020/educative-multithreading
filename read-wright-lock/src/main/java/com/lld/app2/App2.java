package com.lld.app2;

import com.lld.app2.util.ReadWriteLockUtil;

public class App2 {
    public static void main(String[] args) {
        ReadWriteLockUtil r = new ReadWriteLockUtil();

        Thread writeThread = new Thread(() -> r.write(24));
        Thread writeThread2 = new Thread(() -> r.write(25));

        Thread readThread = new Thread(() -> {
            System.out.println(r.read(0));
        });
        Thread readThread2 = new Thread(() -> {
            System.out.println(r.read(1));
        });

        readThread.start();
        readThread2.start();
        writeThread.start();
        writeThread2.start();


    }
}
