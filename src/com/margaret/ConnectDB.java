package com.margaret;

import java.sql.*;

/**
 * Created by sn0173nd on 12/2/2015. This method came from code provided by Clara. I modified it to separate creation of the database under a connection to the server, and then made another connection to the database on the server.
 */
public class ConnectDB {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  // fixed
    static final String DB_CONNECTION_URL = "jdbc:mysql://localhost:3306/";  // fixed
    static final String DB_NAME = "musicschool";  // fixed
    // database created using command line for my sql
    static final String USER = "root";  // TODO Clara - replace root with your user name here
    static final String PASS = "cello"; // TODO Clara - replace cello with your password here
    static Connection conn = null;
    static Statement statement = null;
    public static Statement tableStatement = null;
//    static EnrollDataModel enrollDataModel;

    public ConnectDB() {
        try {
            //Load driver class
            try {
                Class.forName(JDBC_DRIVER);
            } catch (ClassNotFoundException cnfe) {
                System.out.println("No database drivers found. Quitting");
                System.out.println(cnfe);
            }

            conn = DriverManager.getConnection(DB_CONNECTION_URL, USER, PASS);
            statement = conn.createStatement(); // Creates a Statement object for sending SQL statements to the database program t create the database musicschool

            CreateDatabase();
            conn = DriverManager.getConnection(DB_CONNECTION_URL + DB_NAME, USER, PASS);
            statement = conn.createStatement(); // Creates a Statement object for sending SQL statements to the database musicschool
            tableStatement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

            CreateTables createTables = new CreateTables(); // set up the database

        } catch (SQLException se) {
            System.out.println(se);
            se.printStackTrace();
        }
    }

    // this method sends a sql server to the database program to create the database musicschool
    public void CreateDatabase(){
        String createDBStr = "CREATE DATABASE "  + DB_NAME + ";";
        try {
            statement.executeUpdate(createDBStr);
        }
        catch (SQLException sqle){
            System.out.println("Cannot create database MusicSchool " + sqle);
        }
    }
}
