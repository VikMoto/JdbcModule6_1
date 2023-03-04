package jdbcModule6.service;


import org.flywaydb.core.Flyway;

import java.sql.*;

import static jdbcModule6.service.StorageConstants.*;

public class DatabaseInitService {
      public void initDb(){
       // First, create the database if it doesn't exist
       if (!databaseExists()) {
           String createDbSql = "CREATE DATABASE megasoft;";
           try (Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/", DB_USER, DB_PASS);
                PreparedStatement pstmt = conn.prepareStatement(createDbSql)) {
               pstmt.executeUpdate();
           } catch (SQLException e) {
               throw new RuntimeException("Error creating database: " + e.getMessage(), e);
           }
       }
       // Next, run the Flyway migration
        final Flyway flyway = Flyway.configure()
                .dataSource(DB_URL, DB_USER, DB_PASS)
                .baselineOnMigrate(true)
                .load();
        flyway.migrate();

    }

    public static boolean databaseExists() {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
             PreparedStatement pstmt = conn.prepareStatement("SELECT datname FROM pg_database");
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                String dbName = rs.getString("datname");
                if (dbName.equals("megasoft")) {
                    return true;
                }
            }
            return false;
        } catch (SQLException e) {
            throw new RuntimeException("Error checking database existence: " + e.getMessage(), e);
        }
    }

}
