package project.library;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;
public class DataBase {
    static final String databaseName = "library";
    static final String databaseUser = "admin";
    static final String databasePassword = "admin";
    static final String url = "jdbc:mysql://localhost/" + databaseName;
    static Connection databaseLink;

    public static Connection getConnection() throws SQLException{
            databaseLink = DriverManager.getConnection(url, databaseUser, databasePassword);
        return databaseLink;
    }

}
