import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

import static java.nio.file.Files.writeString;

public class App {

    public static void main(String[] args) throws IOException {
        HttpResponse<String> response = Unirest
                .get("https://emex.ru/products/69200H0000/31327")
                .asString();

        System.out.println("STATUS = " + response.getStatus());
        // => STATUS = 200

        System.out.println("HEADER = " + response.getHeaders());
        // => HEADER = Accept-Ranges: bytes   и т.д.

        System.out.println("BODY = " + response.getBody());
        // => BODY = <!doctype html>   и т.д.

        writeString(Paths.get("emex.txt"), response.getBody());

        List<String> list;

    }
}




//            System.out.println(); // => {Petr=1990, Egor=1995, Ivan=2000}

//        System.setOut(new java.io.PrintStream(System.out, true, "UTF-8"));
//        System.out.println(); throws Exception
