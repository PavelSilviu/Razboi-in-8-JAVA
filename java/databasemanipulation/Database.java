package databasemanipulation;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String USER = "Silviu";
    private static final String PASSWORD = "12345345s";
    private static Connection connection = null;

    private Database() {
    }

    public static Connection getConnection() {
        if (connection == null)
            createConnection();
        return connection;
    }

    private static void createConnection() {
        try {
            connection = DriverManager.getConnection(
                    URL, USER, PASSWORD);
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    public static void closeConnection() throws IOException, SQLException {
        if (connection != null) connection.close();
    }
}
