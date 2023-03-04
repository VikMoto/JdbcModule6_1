package jdbcModule6.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static jdbcModule6.service.StorageConstants.*;

public final class ConnectionManager {
//    private static final String DB_URL = "jdbc:postgresql://localhost:5432/flight_repository";
//    private static final String DB_USER = "postgres";
//
//    private static final String DB_PASS = "postgres";
//
    private static final String DB_URL_KEY = "db.url";
    private static final String DB_USER_KEY = "db.username";

    private static final String DB_PASS_KEY = "db.password";

    static {
        loadDriver();
    }

    public ConnectionManager() {
    }

    public static Connection open()  {
        try {
            return DriverManager.getConnection(
                    DB_URL,
                    DB_USER,
                    DB_PASS);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void loadDriver() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
//    public static Connection open()  {
//        try {
//            return DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
}
