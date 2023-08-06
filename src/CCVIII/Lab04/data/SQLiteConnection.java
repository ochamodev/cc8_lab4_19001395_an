package CCVIII.Lab04.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SQLiteConnection {
    private static SQLiteConnection instance;
    private Connection connection;
    private static Logger LOGGER;

    private static final String DB_URL = "jdbc:sqlite:serverdatabase.db";

    public void setLogger(Logger logger) {
        LOGGER = logger;
    }

    public void initDb() {
        Connection conn = getConnection();

        try {
            final var statement = conn.createStatement();
            var domainTable= """
                    CREATE TABLE IF NOT EXISTS Domain(
                        idDomain INTEGER PRIMARY KEY AUTOINCREMENT,
                        domainName TEXT NOT NULL,
                        isThisDomainServer INTEGER NOT NULL
                    )
                """;
            statement.executeUpdate(domainTable);
            var tableMailBox = """
                    CREATE TABLE IF NOT EXISTS MailBox(
                        idMailBox INTEGER PRIMARY KEY AUTOINCREMENT,
                        idDomain INTEGER NOT NULL,
                        userDomainName TEXT,
                        FOREIGN KEY (idDomain)
                            REFERENCES Domain(idDomain)
                    )
                """;
            statement.executeUpdate(tableMailBox);
            var emailTable = """
                    CREATE TABLE IF NOT EXISTS StoredEmail(
                        idStoredEmail INTEGER PRIMARY KEY AUTOINCREMENT,
                        idMailBox INTEGER NOT NULL,
                        subjectMail TEXT NOT NULL,
                        recipients TEXT NOT NULL,
                        emailData TEXT NOT NULL,
                        FOREIGN KEY (idMailBox)
                            REFERENCES MailBox(idMailBox)
                    )
                """;
            statement.executeUpdate(emailTable);
            statement.close();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "[DB_INIT]" + e.getMessage());
            LOGGER.log(Level.WARNING, "[DB_INIT]" + e.getLocalizedMessage());
        }
    }

    private SQLiteConnection() {
        try {
            connection = DriverManager.getConnection(DB_URL);
        } catch (Exception e) {
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
