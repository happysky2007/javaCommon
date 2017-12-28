package StringTest;

public class StringReplace {
    public static void main(String args[]) {
        String test = "rmp1/2/3";
        String temp = test.replaceAll("\\/", " ");
        System.out.println(temp);
    }
    
}
