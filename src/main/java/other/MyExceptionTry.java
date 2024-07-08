package other;

import java.io.IOException;

public class MyExceptionTry {
    public static void main(String[] args) {
        try(Resource resource = new Resource()) {
            throw new RuntimeException();
        } catch (Exception e) {
            System.out.println("Exception caught - " + e);
            for (Throwable throwable : e.getSuppressed()) {
                System.out.println(throwable);
            }
        }
    }

    static class Resource implements AutoCloseable {
        @Override
        public void close() throws Exception {
            System.out.println("Resource closed");
            throw new IOException();
        }
    }
}
