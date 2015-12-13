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

//    public void AddStudent (Student studentToAdd){
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

//            String studentSked = "Select " + CreateTables.CLASS_NAME_COLUMN + ", " + CreateTables.CLASS_DAY_COLUMN + ", " + CreateTables.CLASS_TIME_COLUMN + " from " + Queries.simplerJoin + " where " + CreateTables.STUDENT_LAST_COLUMN + " like '" + studentPickedLast + "' and " + CreateTables.STUDENT_FIRST_COLUMN + " like '" + studentPickedFirst + "'";

            String studentSkedQuery = "Select * from " + Queries.simplerJoin + " where " + CreateTables.STUDENT_TABLE_NAME + "." + CreateTables.STUDENT_PK_COL + " like '" + studentPickedID + "'";
            System.out.println("The get sked rs query is " + studentSkedQuery);
            rs1 = ConnectDB.statement.executeQuery(studentSkedQuery);
            return rs1;
        }
        catch (SQLException sqle){
            System.out.println("In Show Schedule for Student " + sqle);
        }
        return null;
    }

    public void DisplayStudentSked (ResultSet rs){
        ArrayList<String> ClassNameARL = new ArrayList<String>();
        ArrayList<String> DayOfWeekARL = new ArrayList<>();
        ArrayList<String> TimeOfDayARL = new ArrayList<String>();
        ArrayList<Double> classPriceARL = new ArrayList<Double>();
        int rowCount = Queries.GetRowCount(rs);
        try {
           rs.beforeFirst();

            while (rs.next()) {
                ClassNameARL.add(rs.getString(CreateTables.CLASS_NAME_COLUMN));
                DayOfWeekARL.add(rs.getString(CreateTables.CLASS_DAY_COLUMN));
                TimeOfDayARL.add(rs.getString(CreateTables.CLASS_TIME_COLUMN));
            }
            rs.beforeFirst();


            if (rs.next() == false) {
                System.out.println("There are no students in the Database.");
            } else { // then output the results once you know there is a result set
                int loopCount = 0;  // have to count again
                System.out.println("These are the students in the school:");
                for (int i = 0; i < rowCount; i++) {
                    loopCount++;
                    System.out.println(ClassNameARL.get(i) + ". " + DayOfWeekARL.get(i) + " " + TimeOfDayARL.get(i));
                }
            }
        }
        catch (SQLException sqle){
            System.out.println("In display student sked " + sqle);
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
