package com.margaret;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by sn0173nd on 12/4/2015.
 */
public class Teacher {

    private String firstName;
    private String lastName;
    private String phone;

    public Teacher () {}

    public Teacher (String first, String last, String phone){
        this.firstName = first;
        this.lastName = last;
        this.phone = phone;
    }

    public void AddTeacher (){

        try {

            String addDataSQL = "INSERT INTO " + CreateTables.TEACHER_TABLE_NAME + "(" + CreateTables.TEACHER_FIRST_COLUMN + ", " + CreateTables.TEACHER_LAST_COLUMN + ", " + CreateTables.TEACHER_PHONE_COLUMN + ")" + " VALUES ( ?, ?, ?)";
            PreparedStatement addToTe = ConnectDB.conn.prepareStatement(addDataSQL);
            addToTe.setString(1, this.firstName);
            addToTe.setString(2, this.lastName);
            addToTe.setString(3, this.phone);
            addToTe.executeUpdate();
        }
        catch (SQLException se) {
            System.out.println("In Teacher Class Add Student Method " + se);
            se.printStackTrace();
        }
    }

    public ResultSet AllDataQuery(){
        ResultSet returnRS = null;

        try {

            String allDataQuery = "SELECT * FROM " + CreateTables.TEACHER_TABLE_NAME;
            returnRS = ConnectDB.statement.executeQuery(allDataQuery);
            return returnRS;
        }
        catch (SQLException sqle){
            System.out.println("in Teacher All Data Query call to select all " + sqle);
            return returnRS;
        }
    }

    public ResultSet GetSchedule(String teacherPicked) {
        ResultSet rs1;
        try {
            String teacherPickedID = teacherPicked.substring(0, teacherPicked.indexOf(" "));
            String teacherSkedQuery = Queries.teacherSkedJoin + " where " + CreateTables.TEACHER_TABLE_NAME + "." + CreateTables.TEACHER_PK_COL + " like '" + teacherPickedID + "'";
            rs1 = ConnectDB.statement.executeQuery(teacherSkedQuery);
            return rs1;
        }
        catch (SQLException sqle){
            System.out.println("In Show Schedule for Student " + sqle);
        }
        return null;
    }

    public ResultSet ShowSchedule(int teacherPicked) {

        ResultSet rs1;
        try {
            String findClassData = "SELECT * FROM " + CreateTables.TEACHER_TABLE_NAME + " WHERE " + CreateTables.TEACHER_PK_COL + " = ? ";
            PreparedStatement selectRowByID = ConnectDB.conn.prepareStatement(findClassData);
            selectRowByID.setInt(1, teacherPicked);
            rs1 = selectRowByID.executeQuery();
            return rs1;
        }
        catch (SQLException sqle){
            System.out.println("In Show Schedule for Teacher " + sqle);
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
