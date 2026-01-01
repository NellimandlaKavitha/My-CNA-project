package utils;

import java.io.*;
import java.util.Properties;

public class PropertyReader {

    private static Properties loadProperties(String filePath) {
        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream(filePath)) {
            props.load(fis);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return props;
    }

    public static String getProperty(String filePath, String key) {
        Properties props = loadProperties(filePath);
        return props.getProperty(key);
    }

    public static synchronized void setProperty(
            String filePath, String key, String value) {

        Properties props = loadProperties(filePath);
        props.setProperty(key, value);

        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            props.store(fos, "Updated at runtime");
        } catch (Exception e) {
            e.printStackTrace();
            
            
        }
    }
}
