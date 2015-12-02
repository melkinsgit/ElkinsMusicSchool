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

    // Student table name, pk and columns
    public final static String XX_TABLE_NAME = "";
    public final static String XX_PK_COL = "";
    public final static String A_COLUMN = "";

    // Teacher table name, pk and columns
    public final static String YY_TABLE_NAME = "";
    public final static String YY_PK_COL = "";
    public final static String B_COLUMN = "";
    public final static String C_COLUMN = "";

    // Class table name, pk and columns
    public final static String ZZ_TABLE_NAME = "";
    public final static String ZZ_PK_COL = "";
    public final static String D_COLUMN = "";
    public final static String E_COLUMN = "";

    // Student class table name, pks and columns
    public final static String CC_TABLE_NAME = "";
    public final static String CC_PK_COL = "";
    public final static String DD_PK_COLUMN = "";

    // private static HoneyDataModel honeyDataModel;

    public void connect() {
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
        } catch (SQLException se) {
            System.out.println(se);
            se.printStackTrace();
        }
    }
}
