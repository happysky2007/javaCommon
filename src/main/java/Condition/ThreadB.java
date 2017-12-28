package Condition;

public class ThreadB extends Thread {
    private MyService service;
    
    public ThreadB(MyService service) {
        this.service = service;
    }
    
    public void run() {
        System.out.println("Thread B begin " + System.currentTimeMillis());
        service.signalMethod();
        System.out.println("Thread B end  " + System.currentTimeMillis());
        
    }
}
