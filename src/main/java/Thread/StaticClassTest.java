package Thread;

public class StaticClassTest {
    
    public static void main(String args[]) {
        MyClass myclass = new MyClass();
        StaticClass staticClass1 = new StaticClass(myclass);
        staticClass1.start();
        // staticClass1.interrupt();
        
        StaticClass2 staticClass2 = new StaticClass2(myclass);
        staticClass2.start();
    }
}
