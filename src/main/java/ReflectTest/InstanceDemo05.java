package ReflectTest;

import java.lang.reflect.Field;

public class InstanceDemo05 {
    
    public static void main(String[] args) {
        try {
            Class<?> c = Class.forName("ReflectTest.Person");
            Field nameField = c.getDeclaredField("name");
            Field ageField = c.getDeclaredField("age");
            
            Object obj = c.newInstance();
            
            nameField.setAccessible(true);
            nameField.set(obj, "test");
            
            ageField.setAccessible(true);
            ageField.set(obj, 10);
            System.out.println(obj);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
}
