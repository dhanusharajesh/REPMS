package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnUtil {

    private static Connection connection;

    public static Connection getConnection(String connectionString) {

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            connection =
                    DriverManager.getConnection(
                            connectionString,
                            "root",
                            "Ammu@2005"
                    );

        } catch (Exception e) {
            e.printStackTrace();
        }

        return connection;
    }
}