package HexletQA;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

/**
 * передать метод как параметр - 3 способа
 */

public class MethodAsParam {

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


        List<String> list1 = List.of("1", "22", "333", "4444");
        List<String> filteredList1 = filterList(list1, x -> x.length() >= 3);
        System.out.println(filteredList1); // => [333, 4444]

        List<Integer> list2 = List.of(1, 2, 3, 4);
        List<Integer> filteredList2 = filterList(list2, x -> x >= 3);
        System.out.println(filteredList2); // => [3, 4]
    }


    public static <T> List<T> filterList(List<T> list, Predicate<T> rool) {
        return list.stream()
                .filter(x -> rool.test(x))
                .collect(Collectors.toList());
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
