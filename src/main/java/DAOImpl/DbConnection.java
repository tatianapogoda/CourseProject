package DAOImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DbConnection {

    private static DbConnection instance;
    private static Connection connection;
    private String jdbcURL = "jdbc:postgresql://localhost:5432/Shop";
    private String jdbcUsername = "postgres";
    private String jdbcPassword = "qwerty";

    public DbConnection() {
        connection = null;

        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
        public static DbConnection getInstance(){
            try {
                if (instance == null || connection.isClosed()) {
                    instance = new DbConnection();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return instance;
        }

        public Connection getConnection() {
            return connection;
        }

        public void setConnection(Connection connection){
            this.connection = connection;
        }

        public void closeConnection() {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
}

