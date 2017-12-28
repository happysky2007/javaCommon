package CountDownLatchTest;

import java.util.concurrent.CountDownLatch;

public class Driver {
    static CountDownLatch startSignal = new CountDownLatch(10);
    static CountDownLatch doneSignal = new CountDownLatch(10);
    
    public static void main(String args[]) {
        
        for (int i = 0; i < 10; i++) {
            new Thread(new Worker(startSignal, doneSignal)).start();
            ;
        }
        doSomethingElse();
        System.out.println("main in " + System.currentTimeMillis());
        try {
            startSignal.await();
        } catch (InterruptedException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        try {
            System.out.println("wait " + Thread.currentThread().getName() + System.currentTimeMillis());
            doneSignal.await();
            System.out.println("all done " + Thread.currentThread().getName() + System.currentTimeMillis());
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    
    public static void doSomethingElse() {
        try {
            System.out.println("doSomethingElse() begin " + System.currentTimeMillis());
            Thread.sleep(3000);
            System.out.println("doSomethingElse() end " + System.currentTimeMillis());
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
}
