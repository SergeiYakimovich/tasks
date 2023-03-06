package other;

import java.lang.reflect.Proxy;

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
}
