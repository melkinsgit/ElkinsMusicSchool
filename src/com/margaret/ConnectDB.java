package com.margaret;

import java.sql.*;

/**
 * Created by sn0173nd on 12/2/2015.
 */
public class ConnectDB {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  // fixed
    static final String DB_CONNECTION_URL = "jdbc:mysql://localhost:3306/";  // fixed
    static final String DB_NAME = "musicschool";  // fixed
    // database created using command line for my sql
    static final String USER = "root";  // fixed current user name
    static final String PASS = "itecitec"; // cello for home, itecitec for school

    static Connection conn = null;
    static Statement statement = null;

    public ConnectDB() {
        try {
            //Load driver class
            try {
//                String Driver = "com.mysql.jdbc.Driver";
                Class.forName(JDBC_DRIVER);
            } catch (ClassNotFoundException cnfe) {
                System.out.println("No database drivers found. Quitting");
                System.out.println(cnfe);
            }

            conn = DriverManager.getConnection(DB_CONNECTION_URL + DB_NAME, USER, PASS);
            statement = conn.createStatement(); // Creates a Statement object for sending SQL statements to the database

        } catch (SQLException se) {
            System.out.println(se);
            se.printStackTrace();
        }
    }
}
