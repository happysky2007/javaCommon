package Condition;

public class Run {
    public static void main(String args[]) {
        try {
            MyService service = new MyService();
            ThreadA a = new ThreadA(service);
            a.start();
            
            Thread.sleep(2000);
            
            ThreadB b = new ThreadB(service);
            b.start();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    
}
