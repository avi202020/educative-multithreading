package com.lld.app2.util;

public class NonBlockingCounter {
    private SimulatedCAS counter = new SimulatedCAS(0);

    public long get() {
        return counter.getValue();
    }

    public void increment() {
        long currentCounter;

        // keep on looping until we get the currentValue equal to the expected value
        do {
            currentCounter = counter.getValue();
        } while (currentCounter != counter.compareAndSwap(currentCounter, currentCounter + 1));
    }
}
