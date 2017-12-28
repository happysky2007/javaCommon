package GsonTest;

import java.util.Map;

public class Person {
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
    
}

class Duplicate {
    private Map<String, SnsValue> details;
    
    public Map<String, SnsValue> getDetails() {
        return details;
    }
    
    public void setDetails(Map<String, SnsValue> details) {
        this.details = details;
    }
    
}

class SnsValue {
    public SnsValue(String title, String value) {
        this.title = title;
        this.value = value;
    }
    
    private String title;
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getValue() {
        return value;
    }
    
    public void setValue(String value) {
        this.value = value;
    }
    
    private String value;
}