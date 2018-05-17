package com.sn.my;

import java.util.concurrent.CountDownLatch;

public class Driver {
    public static void main(String[] args) throws InterruptedException {
        int N = 10;
        CountDownLatch startSignal = new CountDownLatch(1);
        CountDownLatch doneSignal = new CountDownLatch(N);

        for(int i=0;i<10;++i)
            new Thread(new Worker(startSignal,doneSignal)).start();

        doSomeThingElse();
        startSignal.countDown();
        doSomeThingElse();
        doneSignal.await();

    }

    static void doSomeThingElse() {
        System.out.println("main prepared");
    }
}
