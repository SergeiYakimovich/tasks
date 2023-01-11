package utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.function.UnaryOperator;

public class MethodAsParam { // 3 способа передать метод как параметр

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        superMethod(String::toUpperCase); // => HEXLET
        superMethod(s -> s + "!"); // => hexlet!
        superMethod(MethodAsParam::reverse);  // => telxeh

        Method method = Integer.class.getDeclaredMethod("max", int.class, int.class);
        superMethodReflection(0, method); // => 20
        method = Integer.class.getDeclaredMethod("sum", int.class, int.class);
        superMethodReflection(0, method); // => 30
        method = MethodAsParam.class.getDeclaredMethod("concate", int.class, int.class);
        superMethodReflection(new MethodAsParam(), method); // => 1020

        MyInterface count = (a, b, c) -> a + b + c;
        superMethodInterface(count); // => 35
        superMethodInterface((a,b,c) -> a * b * c); // => 1000
        superMethodInterface((a,b,c) -> a + b - c); // => -5
    }

    // создадим свой интерфейс
    interface MyInterface {
        int count(int a, int b, int c);
    }
    public static void superMethodInterface(MyInterface method) {
        int a = 5, b = 10, c = 20;
        int result = method.count(a, b, c);
        System.out.println(result);
    }

    // воспользуемся готовым функциональным интерфейсом UnaryOperator
    public static void superMethod(UnaryOperator<String> method) {
        String str = "Hexlet";
        String result = method.apply(str);
        System.out.println(result);
    }

    // воспользуемся рефлексией
    public static void superMethodReflection(Object object, Method method) throws InvocationTargetException, IllegalAccessException {
        int a = 10;
        int b = 20;
        int result = (int) method.invoke(object, a, b);
        System.out.println(result);
    }

    public static String reverse(String str) {
        StringBuilder builder = new StringBuilder();
        builder.append(str);
        return builder.reverse().toString();
    }
    public static int concate(int a, int b) {
        return Integer.parseInt("" + a + b);
    }
}