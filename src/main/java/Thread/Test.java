package Thread;

public class Test extends Thread {
    
    @Override
    public void run() {
        // TODO Auto-generated method stub
        
        for (int i = 0; i < 500000; i++) {
            System.out.println("i = " + i);
            try {
                System.out.println(this.currentThread().getName());
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        
        System.out.println("run ===========");
    }
    
    public void print(int i) throws InterruptedException {
        if (this.interrupted()) {
            throw new InterruptedException();
        }
        System.out.println("i = " + i);
    }
    
    public static void main(String args[]) {
        
        Test thread = new Test();
        thread.start();
        
        thread.interrupt();
        System.out.println("stop1 = " + thread.interrupted());
        System.out.println("stop1 = " + thread.interrupted());
        System.out.println("end");
    }
    
}
