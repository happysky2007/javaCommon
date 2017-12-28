package StringTest;

import java.util.HashMap;
import java.util.Map;

class Temp {
    public int num = 50;
}

public class StringTest {
    
    public static void main(String[] args) {
        
        Map<String, String> map = new HashMap<String, String>();
        String str1 = "abc";
        String str2 = new String("abc");
        String str3 = "abc";
        System.out.println(str1 == str2);
        System.out.println(str1 == str3);
        
        Temp temp = new Temp();
        change(temp);
        System.out.println(temp.num);
        
        char array[] = { 'y', 'o', 'u' };
        changeCharArray(array);
        System.out.println(array);
        
        String tempString = "hi";
        changeString(tempString);
        System.out.println(tempString);
        
        String strArr[] = { "how", "code", "?" };
        changeStringArray(strArr);
        System.out.println(strArr[0]);
        
    }
    
    public static void change(Temp temp) {
        // temp.num = 100;
        temp = new Temp();
        temp.num = 51;
    }
    
    public static void changeCharArray(char temp[]) {
        temp[0] = 'M';
    }
    
    public static void changeString(String temp) {
        temp = "ccc";
    }
    
    public static void changeStringArray(String temp[]) {
        temp[0] = "DDD";
    }
    
}
