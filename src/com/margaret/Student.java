package com.margaret;

import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by sn0173nd on 12/2/2015.
 */
public class Student {

    // Student table name, pk and columns
//    public final static String STUDENT_TABLE_NAME = "Student";
//    public final static String STUDENT_PK_COL = "StudentID";
//    public final static String PHONE_COLUMN = "";

    private String firstName;
    private String lastName;
    private String phone;

    public Student (String first, String last, String phone){
        this.firstName = first;
        this.lastName = last;
        this.phone = phone;
    }

    public void CreateTable(){


//        try {
//            if (!studentTableExists()) {
//
//                //Create a student table in the database with TODO columns and name fields
//                String createTableSQL = "CREATE TABLE " + STUDENT_TABLE_NAME + " (" + _PK_COL + " int NOT NULL AUTO_INCREMENT PRIMARY KEY, " + _COLUMN + " varchar(50)" + ")";
//                System.out.println(createTableSQL);
//                statement.executeUpdate(createTableSQL);
//
//                System.out.println("Created hives table");
//
//                String addDataSQL = "INSERT INTO " + STUDENT_TABLE_NAME + "(" + LOCATION_COLUMN + ")" + " VALUES ('Location1')";
//                System.out.println(addDataSQL);
//                statement.executeUpdate(addDataSQL);
//                addDataSQL = "INSERT INTO " + STUDENT_TABLE_NAME + "(" + LOCATION_COLUMN + ")" + " VALUES ('Location2')";
//                System.out.println(addDataSQL);
//                statement.executeUpdate(addDataSQL);
//                addDataSQL = "INSERT INTO " + STUDENT_TABLE_NAME + "(" + LOCATION_COLUMN + ")" + " VALUES ('Location3')";
//                System.out.println(addDataSQL);
//                statement.executeUpdate(addDataSQL);
//            }
//        } catch (SQLException se) {
//            System.out.println(se);
//            se.printStackTrace();
//        }
    }

    public void AddStudent (String First, String Last, String Phone){

        try {

            String addDataSQL = "INSERT INTO " + CreateTables.STUDENT_TABLE_NAME + "(" + CreateTables.STUDENT_FIRST_COLUMN + ", " + CreateTables.STUDENT_LAST_COLUMN + ", " + CreateTables.STUDENT_PHONE_COLUMN + ")" + " VALUES ('" + First + "', '" + Last+ "', '" + Phone + "')";
            System.out.println(addDataSQL);
            ConnectDB.statement.executeUpdate(addDataSQL);
        }
        catch (SQLException se) {
            System.out.println(se);
            se.printStackTrace();
        }
    }


    // GETTERS & SETTERS _______________________________________________________________________________________
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
