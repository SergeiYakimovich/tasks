package HexletQA.io;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Properties;

import static java.nio.file.Files.writeString;

public class MyIO {
    public static void readProperties() throws IOException {
        Properties prop = new Properties();
        prop.load(new FileInputStream("application.properties"));

        String value = prop.getProperty("server.port");
        System.out.println(value); // => ${PORT:8080}

        value = prop.getProperty("rollbar_token");
        System.out.println(value); // => 3749c863ebc141

        value = prop.getProperty("spring.profiles.active");
        System.out.println(value); // => prod

        prop = System.getProperties();
        System.out.println(prop);
    }

    public static void main(String[] args) throws IOException {
        String fileName = "test.txt";
        String text = "Hello!\n";

        Writer myWriter = new FileWriter(fileName, true);
        myWriter.write(text);
        myWriter.close();

        writeString(Paths.get(fileName), text, StandardOpenOption.APPEND);
    }
}
