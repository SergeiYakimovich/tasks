package HexletQA;

import java.io.IOException;

public class ExceptionTasks {

    /**
     * вызов подавленного исключения
     */
    public static void main(String[ ] args) {
        try {
            func();
        } catch (Exception e) {
            System.out.println(e); // => java.io.IOException
            Throwable[] suppressedExceptions = e.getSuppressed();
            for (Throwable t : suppressedExceptions) {
                System.out.println(t); // => java.lang.ArrayIndexOutOfBoundsException: Index 3 out of bounds for length 2
            }
        }
    }

    // метод вызывает ArrayIndexOutOfBoundsException, а затем подавляет ее, выбрасывая IOException
    public static void func() throws Exception {
        Exception supressedExeption = null;
        try {
            int[] arr = {1,2};
            int n = arr[3];
        } catch (Exception exception) {
            supressedExeption = exception;
            throw exception;
        } finally {
            Exception newExeption = new IOException();
            if(supressedExeption != null) {
                newExeption.addSuppressed(supressedExeption);
            }
            throw newExeption;
        }
    }
}
