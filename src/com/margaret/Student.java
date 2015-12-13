package com.margaret;

import org.omg.CORBA.TIMEOUT;

import javax.xml.transform.Result;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by sn0173nd on 12/2/2015.
 */
public class Student {

    private String firstName;
    private String lastName;
    private String phone;

    public Student () {}

    public Student (String first, String last, String phone){
        this.firstName = first;
        this.lastName = last;
        this.phone = phone;
    }

    public void AddStudent (){

        try {

            String addDataSQL = "INSERT INTO " + CreateTables.STUDENT_TABLE_NAME + "(" + CreateTables.STUDENT_FIRST_COLUMN + ", " + CreateTables.STUDENT_LAST_COLUMN + ", " + CreateTables.STUDENT_PHONE_COLUMN + ")" + " VALUES ( ?, ?, ?)";
            PreparedStatement addToSt = ConnectDB.conn.prepareStatement(addDataSQL);
            addToSt.setString(1, this.firstName);
            addToSt.setString(2, this.lastName);
            addToSt.setString(3, this.phone);
            addToSt.executeUpdate();
        }
        catch (SQLException se) {
            System.out.println("In Student Class Add Student Method " + se);
            se.printStackTrace();
        }
    }

    public static ResultSet AllDataQuery(){
        ResultSet returnRS = null;

        try {

            String allDataQuery = "SELECT * FROM " + CreateTables.STUDENT_TABLE_NAME;
            returnRS = ConnectDB.statement.executeQuery(allDataQuery);
            return returnRS;
        }
        catch (SQLException sqle){
            System.out.println("in Student All Data Query call to select all " + sqle);
        }
        return returnRS;
    }

    public ResultSet GetSchedule(String studentPicked) {
        ResultSet rs1;
        try {

            String studentPickedID = studentPicked.substring(0, studentPicked.indexOf(" "));

            String studentSkedQuery = "Select * From " + Queries.studentSkedJoin + " where " + CreateTables.STUDENT_TABLE_NAME + "." + CreateTables.STUDENT_PK_COL + " like '" + studentPickedID + "'";
            rs1 = ConnectDB.statement.executeQuery(studentSkedQuery);
            return rs1;
        }
        catch (SQLException sqle){
            System.out.println("In Show Schedule for Student " + sqle);
        }
        return null;
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
