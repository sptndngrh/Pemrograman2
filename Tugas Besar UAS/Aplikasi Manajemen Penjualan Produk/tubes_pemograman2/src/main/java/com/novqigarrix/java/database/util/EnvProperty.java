package com.novqigarrix.java.database.util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class EnvProperty {

    public static String getProperty(String propertyName) {
        Properties prop = new Properties();
        InputStream input = null;

        try {
            input = Files.newInputStream(Paths.get(".env.properties"));
            prop.load(input);

            return prop.getProperty(propertyName);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
