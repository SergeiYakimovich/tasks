package yandex.hrtech;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Brackets { // #5
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String text = reader.readLine();
        Stack<Character> stack = new Stack<>();
        Map<Character, Character> bracketMap = Map.of(')','(',']','[','}','{');
        Set<Character> openChars = new HashSet<>(bracketMap.values());
        Set<Character> closeChars = bracketMap.keySet();
        for(int i = 0; i < text.length(); i++) {
            char next = text.charAt(i);
            if(openChars.contains(next)) {
                stack.push(next);
                continue;
            }
            if(closeChars.contains(next)) {
                char open = bracketMap.get(next);
                if(stack.size() == 0 || stack.pop() != open) {
                    writer.write("no");
                    writer.close();
                    return;
                }
            }
        }
        String result = stack.size()==0 ? "yes" : "no";
        writer.write(result);

        reader.close();
        writer.close();
    }

}

