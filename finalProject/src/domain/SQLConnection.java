package domain;

import java.sql.*;
import java.util.Properties;

public class SQLConnection {
    private static Connection conn;
    private static Statement statement;
    private static final Object LOCK = new Object();

    public static Statement getStatement()  {
        if(statement != null){
            return statement;
        }

        synchronized (LOCK){
            if(statement != null){
                return statement;
            }

            try {
                Class.forName("org.postgresql.Driver");
                String url = "jdbc:postgresql://localhost:5432/finalProjectJava";

                Properties authorization = new Properties();
                authorization.put("user", "postgres");
                authorization.put("password", "postgres");

                conn = DriverManager.getConnection(url, authorization);
                statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                        ResultSet.CONCUR_UPDATABLE);
            } catch (Exception e) {
                System.err.println("Error accessing database!");
            }
        }

        return statement;
    }

    public static void CloseConnection() {
        try {
            statement.close();
            conn.close();
        } catch (SQLException e) {
            System.err.println("Error closing database!");
        }
    }
}
