package ReflectTest;

public class InstanceDemo01 {
    
    public static void main(String[] args) {
        try {
            Class<?> c = Class.forName("ReflectTest.Person");
            
            Person per = null;
            per = (Person) c.newInstance();
            per.setAge(10);
            per.setName("test name");
            System.out.println(per);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
}
