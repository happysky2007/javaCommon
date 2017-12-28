package ReflectTest;

class X {
    
}

public class ReflectDemo01 {
    
    public static void main(String[] args) {
        try {
            Class<?> c1 = Class.forName("ReflectTest.Person");
            Class<?> c2 = new X().getClass();
            Class<?> c3 = null;
            c3 = X.class;
            
            System.out.println(c1.getName());
            System.out.println(c2.getName());
            System.out.println(c3.getName());
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
}
