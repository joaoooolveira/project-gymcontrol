package com.joaooliveira.gymcontrol.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectDB {
    
    private static final String url = "jdbc:mysql://localhost:3306/gymcontrol";
    private static final String user = "root";
    private static final String password = "Jo23ao0107*";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}
