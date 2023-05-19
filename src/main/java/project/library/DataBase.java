package project.library;
import java.sql.Connection;
import java.sql.DriverManager;

public class DataBase {
    public Connection databaseLink;
    public Connection getConnection(){
        String databaseName = "";
        String databaseUser = "";
        String databasePassword = "";
        String url = "jdbc:mysql://localhost/" + databaseName;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            databaseLink = DriverManager.getConnection(url, databaseUser, databasePassword);
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return databaseLink;
    }
}
