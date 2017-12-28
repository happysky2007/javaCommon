package ReflectTest;

public class Person implements China {
    public void f1(String info) {
        System.out.println(info);
    }
    
    public String f2() {
        // TODO Auto-generated method stub
        System.out.println("f2");
        return null;
    }
    
    public Person() {
        
    }
    
    public Person(String name, int age) {
        super();
        this.name = name;
        this.age = age;
    }
    
    private String name;
    private int age;
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public int getAge() {
        return age;
    }
    
    public void setAge(int age) {
        this.age = age;
    }
    
    @Override
    public String toString() {
        return "Person [name=" + name + ", age=" + age + "]";
    }
    
}
