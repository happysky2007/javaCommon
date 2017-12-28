package Condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyService {
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    
    public void testMethod() {
        try {
            lock.lock();
            System.out.println("A" + System.currentTimeMillis());
            condition.await();
            System.out.println("B" + System.currentTimeMillis());
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    
    public void signalMethod() {
        try {
            lock.lock();
            System.out.println("signal Method begin " + System.currentTimeMillis());
            condition.signalAll();
            System.out.println("signal Method end " + System.currentTimeMillis());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    
}
