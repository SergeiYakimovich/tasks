package io;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JsonWriter { // парсинг JSON файлов
    public static void main(String[] args) throws IOException {
        String fileName = "test.json";
        List<Person> people = new ArrayList<>();
        Person person = new Person("Ivan", 20, Map.of("tel","25-12-86","mail","1@mail.ru"));
        people.add(person);
        person = new Person("Petr", 25, Map.of("tel","35-32-16","mail","2@mail.ru"));
        people.add(person);

        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File(fileName), people);

        List<Person> people1 = mapper.readValue(new File(fileName), new TypeReference<>(){});
        System.out.println(people1.get(0).contacts);
        System.out.println(people1.get(1).name);

    }
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
    static class Person {
        String name;
        int age;
        Map<String, String> contacts;

        public Person(String name, int age, Map<String, String> contacts) {
            this.name = name;
            this.age = age;
            this.contacts = contacts;
        }
    }
}

