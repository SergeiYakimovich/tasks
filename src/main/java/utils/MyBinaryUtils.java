package utils;

/**
 * задачи на двоичные функции
 */
public class MyBinaryUtils {

    /**
     * двоичное сложение
     */
    public static String addBinary(String a, String b) {
        String res = "";
        int i=0;
        int al = a.length();
        int bl = b.length();
        int count = 0;
        while(i<al && i<bl) {
            int aNext = i<al ? a.charAt(al-1-i) - '0': 0;
            int bNext = i<bl ? b.charAt(bl-1-i) - '0': 0;
            count = count + aNext + bNext;
            res = String.valueOf(count % 2 )+ res;
            count = count / 2;
            i++;
        }
        return count==0 ? res : "1"+res;
    }

}
