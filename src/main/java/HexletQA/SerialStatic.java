package HexletQA;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

class MyClass implements Serializable {
    private int nonStaticField;
    private static int staticField = 0;

    public MyClass(int nonStaticFieldValue, int staticFieldValue) {
        this.nonStaticField = nonStaticFieldValue;
        staticField = staticFieldValue;
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeObject(staticField);
    }

    private void readObject(ObjectInputStream in) throws ClassNotFoundException, IOException {
        in.defaultReadObject();
        staticField = (int) in.readObject();
    }

    @Override
    public String toString() {
        return "MyClass {" +
                " nonStaticField=" + nonStaticField +
                " staticField=" + staticField +
                " }";
    }
}

public class SerialStatic {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        MyClass myObject = new MyClass(10, 20);
        FileOutputStream fileOutputStream = new FileOutputStream("test.ser");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(myObject);
        objectOutputStream.close();

        FileInputStream fileInputStream = new FileInputStream("test.ser");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        MyClass myNewObject = (MyClass) objectInputStream.readObject();
        objectInputStream.close();

        System.out.println(myNewObject); // => MyClass { nonStaticField=10 staticField=20 }
    }
}
