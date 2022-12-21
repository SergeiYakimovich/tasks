import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws IOException {
        String fileName = "test.csv";
        List<Person> persons = new ArrayList<>();
        Person person = new Person("Ivan", 20, Map.of("tel","25-12-86","mail","1@mail.ru"));
        persons.add(person);

        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File(fileName), persons);


    }
}
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
class Person {
    String name;
    int age;
    Map<String, String> contacts;

    public Person(String name, int age, Map<String, String> contacts) {
        this.name = name;
        this.age = age;
        this.contacts = contacts;
    }
}

//            System.out.println(); // => {Petr=1990, Egor=1995, Ivan=2000}

//        System.setOut(new java.io.PrintStream(System.out, true, "UTF-8"));
//        System.out.println(); throws Exception
