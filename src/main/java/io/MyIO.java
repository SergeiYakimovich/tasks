package io;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import static java.nio.file.Files.writeString;

public class MyIO {
    public static void main(String[] args) throws IOException {
        String fileName = "test.txt";
        String text = "Hello!\n";

        Writer myWriter = new FileWriter(fileName, true);
        myWriter.write(text);
        myWriter.close();

        writeString(Paths.get(fileName), text, StandardOpenOption.APPEND);
    }
}
