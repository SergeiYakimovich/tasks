package other;

import lombok.Builder;
import lombok.ToString;

@Builder(toBuilder = true)
@ToString
public class MyLombok {
    private String name;
    private int age;

    public static void main(String[] args) {
        MyLombok myEntity = MyLombok.builder().name("Petr").age(1995).build();
        MyLombokBuilder builder = myEntity.toBuilder();
        MyLombok myEntity2 = builder.age(2000).build();
        System.out.println(myEntity2);
    }
}
