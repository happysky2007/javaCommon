package ReflectTest;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class InstanceDemo03 {
    
    public static void main(String[] args) {
        try {
            Class<?> c = null;
            c = Class.forName("ReflectTest.Point");
            
            Point p = null;
            Constructor con[] = null;
            con = c.getConstructors();
            p = (Point) con[0].newInstance();
            Point p2 = (Point) con[0].newInstance();
            System.out.println(p);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
}
