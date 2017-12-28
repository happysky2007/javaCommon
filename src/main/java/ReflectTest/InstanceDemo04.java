package ReflectTest;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class InstanceDemo04 {
    
    public static void main(String[] args) {
        try {
            Class<?> c = Class.forName("ReflectTest.Person");
            
            Class<?> c2[] = c.getInterfaces();
            Class<?> c3 = c.getSuperclass();
            for (int i = 0; i < c2.length; i++) {
                System.out.println(c2[i].getName());
            }
            
            System.out.println(c3.getName());
            
            Constructor constructor[] = c.getConstructors();
            Class p[] = null;
            
            for (int i = 0; i < constructor.length; i++) {
                System.out.println(constructor[i]);
                System.out.println(constructor[i].getModifiers());
                System.out.println(constructor[i].getName());
                
                p = constructor[i].getParameterTypes();
                
            }
            
            for (int i = 0; i < p.length; i++) {
                System.out.println(p[i].getName());
            }
            Method m[] = c.getMethods();
            
            for (int i = 0; i < m.length; i++) {
                System.out.println(m[i]);
                System.out.println(m[i].getExceptionTypes());
            }
            
            Field f1[] = c.getFields();
            for (int i = 0; i < f1.length; i++) {
                System.out.println(f1[i]);
            }
            
            Field f2[] = c.getDeclaredFields();
            for (int i = 0; i < f2.length; i++) {
                System.out.println(f2[i]);
            }
            
            Method fun1 = c.getMethod("f1", String.class);
            fun1.invoke(c.newInstance(), "he");
            
            Method fun2 = c.getMethod("f2");
            fun2.invoke(c.newInstance());
            
        } catch (ClassNotFoundException e) {
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
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
}
