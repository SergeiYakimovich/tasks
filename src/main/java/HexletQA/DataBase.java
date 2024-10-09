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
                    .getConnection("jdbc:postgresql://localhost:15432/mydb?currentSchema=base",
                            "root", "root");

            statement = connection.createStatement();
//            ResultSet result1 = statement.executeQuery("INSERT INTO base.users(name, address) VALUES ('Jone','Chicago');");

            ResultSet result = statement.executeQuery( "SELECT * FROM USERS;" );

            while (result.next()) {
                String  name = result.getString("name");
                String  address = result.getString("address");
//                int age  = result.getInt("age");

                System.out.print( "NAME = " + name );
                System.out.print( "   ADDRESS = " + address );
//                System.out.println( "   AGE = " + age );
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
