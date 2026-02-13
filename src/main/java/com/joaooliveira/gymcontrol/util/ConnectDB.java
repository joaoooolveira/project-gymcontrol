package com.joaooliveira.gymcontrol.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
    
    private static String url = "jdbc:mysql://localhost/gymcontrol";
    private static String user = "root";
    private static String password = "Jo23ao0107*";
    
    
    public static Connection getConnection()throws SQLException{
        return DriverManager.getConnection(url, user, password);
    }
}
