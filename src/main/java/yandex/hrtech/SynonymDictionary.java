package yandex.hrtech;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

public class SynonymDictionary { // #1
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        Map<String, String> map = new HashMap<>();

        int n = Integer.parseInt(reader.readLine());
        for (int i = 0; i < n; i++) {
            String[] s = reader.readLine().split(" ");
            map.put(s[0], s[1]);
            map.put(s[1], s[0]);
        }
        String word = reader.readLine();

        System.out.println(map.get(word));

        reader.close();
        writer.close();
    }
}


