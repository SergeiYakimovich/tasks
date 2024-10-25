package yandex.context5;

import java.io.*;

public class CompressWord {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] parts = reader.readLine().split("");
        String str = parts[0];
        int count = 1;
        StringBuilder result = new StringBuilder();
        for (int i = 1; i < parts.length; i++) {
            if (parts[i].equals(str)) {
                count++;
            } else {
                String prefix = count == 1 ? "" : String.valueOf(count);
                result.append(prefix).append(str);
                count = 1;
                str = parts[i];
            }
        }
        String prefix = count == 1 ? "" : String.valueOf(count);
        result.append(prefix).append(str);
        System.out.println(result);


        reader.close();
        writer.close();
    }
}
