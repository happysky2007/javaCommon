package Stop;

public class PrintString {
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
    
    public static void main(String args[]) {
        PrintString printString = new PrintString();
        printString.printStringMethod();
        System.out.println("thread stop " + Thread.currentThread().getName());
        printString.setContinuePrint(false);
    }
}
