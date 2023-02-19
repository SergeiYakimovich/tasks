import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class App {
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
                System.out.print( "   AGE = " + age );
                System.out.println();
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




//            System.out.println(); // => {Petr=1990, Egor=1995, Ivan=2000}

//        System.setOut(new java.io.PrintStream(System.out, true, "UTF-8"));
//        System.out.println(); throws Exception
