package app;

import util.DBConnUtil;
import util.DBPropertyUtil;

import java.sql.Connection;

public class TestConnection {

    public static void main(String[] args) {

        String path = "src/main/resources/db.properties";

        String connectionString =
                DBPropertyUtil.getConnectionString(path);

        Connection conn =
                DBConnUtil.getConnection(connectionString);

        if (conn != null) {
            System.out.println("Database Connected Successfully!");
        } else {
            System.out.println("Connection Failed!");
        }
    }
}