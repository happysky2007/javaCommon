package StringTest;

public class StringSplitTest {
    
    public static void main(String[] args) {
        String str = "|ALL|";
        String productIdArray[] = str.split("\\|");
        for (int i = 0; i < productIdArray.length; i++) {
            System.out.println("值是:" + productIdArray[0]);
        }
        
    }
    
}
