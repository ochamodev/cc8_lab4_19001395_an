package CCVIII.Lab04.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLiteConnection {
    private static SQLiteConnection instance;
    private Connection connection;

    private static final String DB_URL = "jdbc:sqlite:/ServerDB.db";

    public void initDb() {
        Connection connection = getConnection();

        try {
            final var statement = connection.createStatement();
            var domains = """
                    CREATE TABLE IF NOT
                """;
            statement.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private SQLiteConnection() {
        try {
            connection = DriverManager.getConnection(DB_URL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static synchronized SQLiteConnection getInstance() {
        if (instance == null) {
            instance = new SQLiteConnection();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
    
    public void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Other methods for working with the SQLite database
}
