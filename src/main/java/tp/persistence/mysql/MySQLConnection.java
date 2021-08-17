package tp.persistence.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection {

    private final static String jdbcString      = System.getenv("MYSQL_JDBC");
    private final static String mysqlUsername   = System.getenv("MYSQL_USERNAME");
    private final static String mysqlPassword   = System.getenv("MYSQL_PASSWORD");

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(jdbcString, mysqlUsername, mysqlPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.exit(500);
        return null;
    }

}
