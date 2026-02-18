package com.joaooliveira.gymcontrol.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.InputStream;

public class ConnectDB {
    
    public static String url;
    public static String user;
    public static String password;
    
    static{
        try{
            Properties properties = new Properties();
            
            InputStream input = ConnectDB.class
                    .getClassLoader()
                    .getResourceAsStream("application.properties");
            
            if(input == null){
                throw new RuntimeException("application.properties not found.");
            }
            
            properties.load(input);
            
            url = properties.getProperty("db.url");
            user = properties.getProperty("db.user");
            password = properties.getProperty("db.password");
        } catch(Exception e){
            throw new RuntimeException("Error loading database configuration", e);
        }
    }
    
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}
