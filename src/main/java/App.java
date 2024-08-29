import io.vavr.collection.Stream;
import org.apache.commons.lang3.StringUtils;
import utils.MyDateTimeUtils;
import utils.MyStreamUtils;

import java.io.BufferedReader;
import java.io.File;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.stream.*;

@SuppressWarnings("SameParameterValue")
public class App {

    public static void main(String[] args) throws Exception {
        String str = "k1=v1;k2=v2;k3=v3";
        Map<String, String> map = parse(str);
        System.out.println(map);
    }

    public static Map<String, String> parse(String str) {
        return Arrays.stream(str.split(";"))
                .map(s -> s.split("="))
                .collect(Collectors.toMap(s -> s[0], s -> s[1]));
    }





}






//            System.out.println(); // => {Petr=1990, Egor=1995, Ivan=2000}

//        System.setOut(new java.io.PrintStream(System.out, true, "UTF-8"));
//        System.out.println(); throws Exception
