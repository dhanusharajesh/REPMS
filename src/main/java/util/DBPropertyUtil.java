package util;

import java.io.InputStream;
import java.io.IOException;
import java.util.Properties;

public class DBPropertyUtil {

    public static String getConnectionString(String fileName) {

        Properties properties = new Properties();

        try {

            InputStream input =
                    DBPropertyUtil.class
                            .getClassLoader()
                            .getResourceAsStream("db.properties");

            if(input == null){
                System.out.println("DB PROPERTIES FILE NOT FOUND");
                return null;
            }

            properties.load(input);

            return properties.getProperty("db.url");

        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}