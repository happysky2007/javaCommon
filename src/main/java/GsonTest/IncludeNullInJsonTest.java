package GsonTest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class IncludeNullInJsonTest {
    public static void main(String args[]) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        
        Gson gson = gsonBuilder.serializeNulls().create();
        Person person = new Person();
        // person.setAge(1);
        person.setName("testName");
        String jsonResult = gson.toJson(person);
        System.out.println(jsonResult);
        
    }
    
}
