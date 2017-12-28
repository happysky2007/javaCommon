package Communication;

public class MyObject {
    
    public synchronized void f1() {
        for (int i = 0; i < 10; i++) {
            System.out.println("f1() " + i);
        }
    }
    
    public synchronized void f2() {
        for (int i = 0; i < 10; i++) {
            System.out.println("f2() " + i);
        }
    }
    
}
