package yandex.hrtech;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Tourism { // #6
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(reader.readLine());
        int[] arrX = new int[n];
        int[] arrY = new int[n];
        for (int i = 0; i < n; i++) {
            String[] next = reader.readLine().split(" ");
            arrX[i] = Integer.parseInt(next[0]);
            arrY[i] = Integer.parseInt(next[1]);
        }

        int[] forward = new int[n];
        forward[0] = arrY[0];
        for (int i = 1; i < n; i++) {
            forward[i] = forward[i-1];
            if(arrY[i]>arrY[i-1]) {
                forward[i] += arrY[i]-arrY[i-1];
            }
        }

        int[] back = new int[n];
        back[n-1] = arrY[n-1];
        for (int i = n-2; i >= 0; i--) {
            back[i] = back[i+1];
            if(arrY[i]>arrY[i+1]) {
                back[i] += arrY[i]-arrY[i+1];
            }
        }


        int m = Integer.parseInt(reader.readLine());
        for (int i = 0; i < m; i++) {
            String[] next = reader.readLine().split(" ");
            int p1 = Integer.parseInt(next[0]) - 1;
            int p2 = Integer.parseInt(next[1]) - 1;

            int sum = 0;
            if(p1<p2) {
                sum = forward[p2] - forward[p1];
            }
            if(p1>p2) {
                sum = back[p2] - back[p1];
            }

            writer.write(String.valueOf(sum) + "\n");
        }

        reader.close();
        writer.close();
    }

}
