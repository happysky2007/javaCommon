package LuceneUtilTest;

public class ConditionBreak {
    
    public static void main(String args[]) {
        System.out.println("print");
        for (int i = 0; i < 1000; i++) {
            System.out.println(i);
        }
        testMethod1();
        System.out.println("end");
    }
    
    public static String testMethod1() {
        System.out.println("testMethod1");
        return "method1";
    }
}
