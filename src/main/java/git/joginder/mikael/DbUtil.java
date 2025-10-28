package git.joginder.mikael;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//get db connection
public class DbUtil {

    //to enable import of .env
    private static final Dotenv dotenv = Dotenv.load();

    private static final String URL = dotenv.get("DB_URL");
    private static final String USER = dotenv.get("DB_USER");
    private static final String PASSWORD = dotenv.get("DB_PASS");


    public static Connection getConnection(){
        try{
           //Class.forName("org.postgresql.Driver");
           return DriverManager.getConnection(URL, USER, PASSWORD);
        }catch (SQLException e){
            IO.println("Database connection Error" + e.getMessage());
            return null;
        }
    }
}
