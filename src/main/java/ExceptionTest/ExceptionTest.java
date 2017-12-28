package ExceptionTest;

public class ExceptionTest {
    
    public static void main(String[] args) {
        test();
        try {
            f1();
        } catch (MyException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    
    public static void test() throws MyRuntimeException {
        System.out.println("test");
    }
    
    public static void f1() throws MyException {
        System.out.println("f1");
    }
    
}
