package commonLibs.utils;

import java.io.*;
import java.util.Properties;

public class ConfigUtils {
    public static Properties readProperties (String fileName) throws Exception {
        fileName = fileName.trim();

        InputStream fileReader = new FileInputStream(fileName);
        Properties properties = new Properties();
        properties.load(fileReader);
        return properties;
    }
}
