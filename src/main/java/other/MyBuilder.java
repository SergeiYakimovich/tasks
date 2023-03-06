package other;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
class MyClass {
    String name;
    int id;
}
public class MyBuilder {
    public static void main(String[] args) {
        MyClass a = MyClass.builder().id(1).name("A").build();
        System.out.println(a);
    }
}
