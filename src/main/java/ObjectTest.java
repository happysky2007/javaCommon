import java.util.HashMap;
import java.util.Map;

public class ObjectTest {
    public static void main(String args[]) {
        Object obj = new Object();
        Map<Person, String> myMap = new HashMap<Person, String>();
        Person p1 = new Person("h1", 21);
        Person p2 = new Person("h1", 21);
        myMap.put(p1, "11");
        myMap.put(p2, "22");
        myMap.put(null, "33");
        System.out.println(myMap.toString());
        System.out.println(myMap.get(null));
    }
    
}
