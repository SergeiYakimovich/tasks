package HexletQA;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DataBase {
    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/mydb",
                            "postgres", "");

            statement = connection.createStatement();
            ResultSet result = statement.executeQuery( "SELECT * FROM CARS;" );

            while (result.next()) {
                String  name = result.getString("name");
                String  color = result.getString("color");
                int age  = result.getInt("age");

                System.out.print( "NAME = " + name );
                System.out.print( "   COLOR = " + color );
                System.out.println( "   AGE = " + age );
                // => NAME = VW   COLOR = white   AGE = 3
                // => NAME = TOYOTA   COLOR = black   AGE = 4
            }

            result.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
        }
    }
}
