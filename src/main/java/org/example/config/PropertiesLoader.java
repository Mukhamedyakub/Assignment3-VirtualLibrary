package org.example.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesLoader {


    public static void loadProperties(String fileName){
        Properties props = new Properties();
        try {
            String basePath = new File("").getAbsolutePath()+"\\src\\main\\resources\\";
            FileInputStream input = new FileInputStream(basePath+fileName);
            props.load(input);
        } catch (IOException e) {
            props = null;
            e.printStackTrace();
        }
        Config.properties = props;
    }

}
