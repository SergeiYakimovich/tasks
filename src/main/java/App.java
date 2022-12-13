import java.util.Arrays;
import java.util.Random;

public class App {
    public static void main(String[] args) {
        Integer[] arr = {1, 2};
        Random random = new Random();

        for(int i = 0; i < arr.length - 1; i++) {
            int index = random.nextInt(i + 1, arr.length );
            int temp = arr[i];
            arr[i] = arr[index];
            arr[index] = temp;
        }

        System.out.println(Arrays.toString(arr)); // => [4, 5, 1, 6, 3, 7, 2]
    }
}


//            System.out.println(); // => {Petr=1990, Egor=1995, Ivan=2000}

//        System.setOut(new java.io.PrintStream(System.out, true, "UTF-8"));
//        System.out.println(); throws Exception
