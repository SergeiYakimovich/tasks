package yandex.hrtech;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;
import java.util.stream.Collectors;

public class Polyglot { // #3
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        Map<String, Set<Integer>> languageMap = new HashMap<>();
        int numberOfStudents = Integer.parseInt(reader.readLine());
        for(int i = 0; i < numberOfStudents; i++) {
            int numberOfLanguages = Integer.parseInt(reader.readLine());
            for(int j = 0; j < numberOfLanguages; j++) {
                String language = reader.readLine();
                Set<Integer> students = languageMap.get(language);
                if(students==null) {
                    students=new HashSet<>();
                    students.add(i);
                    languageMap.put(language, students);
                } else {
                    students.add(i);
                }
            }
        }

        Map<String, Set<Integer>> map1 = languageMap.entrySet().stream()
                .filter(x -> x.getValue().size() == numberOfStudents)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        System.out.println(map1.size());
        map1.keySet().forEach(System.out::println);


        System.out.println(languageMap.size());
        languageMap.keySet().forEach(System.out::println);

        reader.close();
        writer.close();
    }

}
