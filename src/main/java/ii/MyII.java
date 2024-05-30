package ii;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

public class MyII {

    public static List<Student> filterStudentsByAge(List<Student> studentList, int age) {
        return studentList.stream().filter(student -> student.getAge() == age).collect(Collectors.toList());
    }

    @Builder
    @Data
    public static class Student {
        String name;
        int age;
    }
}
