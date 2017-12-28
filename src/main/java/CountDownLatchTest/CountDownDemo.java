package CountDownLatchTest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Description
 * 
 * @author usr1999 2015-1-13
 */
public class CountDownDemo {
    
    /** 
     *  
     */
    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(2);
        
        Runner run1 = new Runner("abtest", 3000, latch);
        Runner run2 = new Runner("unittest", 5000, latch);
        
        ExecutorService exec = Executors.newFixedThreadPool(2);
        exec.submit(run1);
        exec.submit(run2);
        
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println("abtest and unittest completed running");
        exec.shutdown();
    }
}

class Runner implements Runnable {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
    String name;
    int time;
    CountDownLatch latch;
    
    public Runner(String name, int time, CountDownLatch latch) {
        this.name = name;
        this.time = time;
        this.latch = latch;
    }
    
    public void run() {
        System.out.println(name + " is ready to run at " + sdf.format(new Date()));
        doRun();
        System.out.println(name + " has runed at " + sdf.format(new Date()));
        latch.countDown(); // 计数递减
    }
    
    private void doRun() {
        try {
            TimeUnit.MILLISECONDS.sleep(time);
            // Thread.sleep(time);
        } catch (InterruptedException e) {
            System.out.println(e.getStackTrace());
        }
    }
}