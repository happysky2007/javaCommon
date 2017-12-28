package Thread;

public class MyClass {
    
    public static void f1() {
        synchronized (MyClass.class) {
            System.out.println("------------");
        }
        System.out.println("f1()");
        for (int i = 0; i < 50; i++) {
            System.out.println("i  = " + i);
        }
    }
    
    public static synchronized void f2() {
        System.out.println("f2()");
        
    }
    
    public static synchronized void f3() {
        System.out.println("f3()");
    }
}
