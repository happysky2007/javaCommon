package GsonTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class GsonTest {
    
    public static void main(String args[]) {
        // MaxTest
        Duplicate foo = new Duplicate();
        Map<String, SnsValue> details = new HashMap<String, SnsValue>();
        details.put("111", new SnsValue("111", "111-v"));
        details.put("222", new SnsValue("222", "222-v"));
        details.put("333", new SnsValue("333", "333-v"));
        foo.setDetails(details);
        
        Gson gson_m = new Gson();
        System.out.println(gson_m.toJson(foo));
        
        String str1 = "[{\"name\":\"kevin\",\"age\":25},{\"name\":\"cissy\",\"age\":24}]";
        String str = "{\"name\":\"kevin\",\"age\":25}";
        Gson gson = new Gson();
        System.out.println(str);
        Person person = gson.fromJson(str, Person.class);
        
        System.out.println(person.getAge());
        
        JsonParser parser = new JsonParser();
        // 将JSON的String 转成一个JsonArray对象
        JsonArray jsonArray = parser.parse(str1).getAsJsonArray();
        ArrayList<Person> personList = new ArrayList<Person>();
        
        for (JsonElement onePerson : jsonArray) {
            Person bean = gson.fromJson(onePerson, Person.class);
            personList.add(bean);
        }
        Iterator<Person> personIt = personList.iterator();
        while (personIt.hasNext()) {
            Person p = personIt.next();
            System.out.println(p.getName());
        }
        
        String test = "212 001 00";
        System.out.println(test.replace(" ", "\\ "));
        
        Gson gson2 = new Gson();
        Person per2 = new Person();
        per2.setAge(1);
        per2.setName("null");
        String result = gson2.toJson(per2);
        System.out.println(result);
        
    }
    
}
