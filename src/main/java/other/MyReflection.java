package other;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.lang.reflect.Field;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.List;

public class MyReflection {
    interface IApple {
        String getColor();
    }
    private static class Apple implements IApple {
        private String color = "red";
        public String getColor() {
            return color;
        }
    }

    public static void main(String[] args) {

        Object proxyInstance = Proxy.newProxyInstance(
                MyReflection.class.getClassLoader(),
                Apple.class.getInterfaces(), (proxy, method, args1) -> {
                    System.out.println("Called getColor() method on Apple");
                    return method.invoke(new Apple(), args1);
                });

        IApple appleProxy = (IApple) proxyInstance;
        System.out.println(appleProxy.getColor());


    }

    public static void main1(String[] args) throws NoSuchFieldException, IllegalAccessException {
        @Data
        @AllArgsConstructor
        class Car {
            String brand;
            String model;

            public static List<String> carFields = List.of("brand", "model");

            void validate() throws NoSuchFieldException, IllegalAccessException {
                for(String carField : carFields) {
                    Field field = this.getClass().getDeclaredField(carField);
                    field.setAccessible(true);
                    String str = (String) field.get(this);
                    if(str == null || str.isBlank()) {
                        System.out.println(this.toString() + field.getName() + " blank");
                    }
                }
            }
        }

        Car car1 = new Car("BMW", "");
        car1.validate();

        Car car2 = new Car("", "Polo");
        car2.validate();

        Car car3 = new Car("Ford", "Focus");
        car3.validate();

        Car car4 = new Car(null, "Focus");
        car4.validate();
    }

    public static void clearString(String password) {
        try {
            Field value = String.class.getDeclaredField("value");
            value.setAccessible(true);
            byte[] chars = (byte[]) value.get(password);
            Arrays.fill(chars, (byte) '*');
        } catch (Exception e) {
            throw new AssertionError(e);
        }
    }
}
