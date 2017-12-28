package CountDownLatchTest;

import java.util.concurrent.CountDownLatch;

public class Worker implements Runnable {
    private final CountDownLatch startSignal;
    private final CountDownLatch doneSignal;
    
    public Worker(CountDownLatch startSignal, CountDownLatch doneSignal) {
        this.startSignal = startSignal;
        this.doneSignal = doneSignal;
    }
    
    public void run() {
        startSignal.countDown();
        System.out.println("start run " + Thread.currentThread().getName() + System.currentTimeMillis());
        doWork();
        System.out.println("end run " + Thread.currentThread().getName() + " " + System.currentTimeMillis());
        doneSignal.countDown();
    }
    
    public void doWork() {
        System.out.println("do work in " + Thread.currentThread().getName());
        System.out.println("do Work");
    }
    
}
