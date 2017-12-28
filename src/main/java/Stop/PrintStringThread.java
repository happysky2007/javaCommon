package Stop;

public class PrintStringThread implements Runnable {
    private boolean isContinuePrint = true;
    
    public void setContinuePrint(boolean isContinuePrint) {
        this.isContinuePrint = isContinuePrint;
    }
    
    public void printStringMethod() {
        while (isContinuePrint == true) {
            System.out.println("run thread " + Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        
    }
    
    public void run() {
        // TODO Auto-generated method stub
        printStringMethod();
    }
    
    public static void main(String args[]) {
        PrintStringThread printString = new PrintStringThread();
        new Thread(printString).start();
        System.out.println("thread stop " + Thread.currentThread().getName());
        printString.setContinuePrint(false);
    }
}
