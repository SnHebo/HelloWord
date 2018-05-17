package com.sn.my;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class Worker implements Runnable {
    private final CountDownLatch startSignal;
    private final CountDownLatch doneSignal;

    public Worker(CountDownLatch startSignal, CountDownLatch doneSignal) {
        this.startSignal = startSignal;
        this.doneSignal = doneSignal;
    }
    void doWork() {
        System.out.println("start work..............................");
    }
    @Override
    public void run() {
        try {
            this.startSignal.await();
            doWork();
            this.doneSignal.countDown();
        } catch (InterruptedException io) {

        }
    }
}
