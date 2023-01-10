package com.lld.app.util;

import java.util.PriorityQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CallbackExecutor {
    private PriorityQueue<Callback> callbackQueue;
    private Lock lock;
    private Condition newCallbackRegistrationArrived;

    public CallbackExecutor() {
        this.callbackQueue = new PriorityQueue<>((o1, o2) -> (int) (o1.getExecuteAt() - o2.getExecuteAt()));
        this.lock = new ReentrantLock();
        this.newCallbackRegistrationArrived = lock.newCondition();
    }

    public void registerCallback(Callback callback) {
        lock.lock();
        callbackQueue.add(callback);
        newCallbackRegistrationArrived.signal();
        lock.unlock();
    }

    public void start() throws InterruptedException {
        long awaitTime = 0;

        while (true) {
            // locking critical section
            lock.lock();

            // If no item in the queue then wait indefinitely for callback to arrive
            while (callbackQueue.size() == 0) {
                newCallbackRegistrationArrived.await();
            }

            // loop till all the callbacks have been executed
            while (callbackQueue.size() != 0) {

                // find min time the execution thread should sleep until callback time arrives
                awaitTime = calculateAwaitTime();

                // break through the loop and execute the callback if callback time is arrived
                if (awaitTime <= 0) {
                    break;
                }

                // wait for the callback time to arrive
                newCallbackRegistrationArrived.await(awaitTime, TimeUnit.MILLISECONDS);
            }

            // execute the callback method
            Callback callback = callbackQueue.poll();
            System.out.println(callback.getMessage() + " Executing at : " + System.currentTimeMillis()/1000);

            // unlock the critical section
            lock.unlock();
        }
    }

    private long calculateAwaitTime() {
        return this.callbackQueue.peek().getExecuteAt() - System.currentTimeMillis();
    }
}
