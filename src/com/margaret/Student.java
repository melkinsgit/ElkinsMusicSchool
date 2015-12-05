package com.margaret;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by sn0173nd on 12/2/2015.
 */
public class Student {

    private String firstName;
    private String lastName;
    private String phone;
    private StudentTableModel studentTableModel;

    public Student (String first, String last, String phone){
        this.firstName = first;
        this.lastName = last;
        this.phone = phone;
        StudentGUI studentTableGUI = new StudentGUI(studentTableModel);
    }

    public void AddStudent (String First, String Last, String Phone){

        try {

            String addDataSQL = "INSERT INTO " + CreateTables.STUDENT_TABLE_NAME + "(" + CreateTables.STUDENT_FIRST_COLUMN + ", " + CreateTables.STUDENT_LAST_COLUMN + ", " + CreateTables.STUDENT_PHONE_COLUMN + ")" + " VALUES ('" + First + "', '" + Last+ "', '" + Phone + "')";
            ConnectDB.statement.executeUpdate(addDataSQL);
        }
        catch (SQLException se) {
            System.out.println(se);
            se.printStackTrace();
        }
    }

    public ResultSet AllDataQuery(){
        ResultSet returnRS = null;

        try {

            String allDataQuery = "SELECT * FROM " + CreateTables.STUDENT_TABLE_NAME;
            returnRS = ConnectDB.statement.executeQuery(allDataQuery);

            if (studentTableModel == null) {
                //If no current movieDataModel, then make one
                studentTableModel = new StudentTableModel(returnRS);
            } else {
                //Or, if one already exists, update its ResultSet
                studentTableModel.updateResultSet(returnRS);
            }
            return returnRS;
        }
        catch (SQLException sqle){
            System.out.println("in Student All Data Query call to select all " + sqle);
            return returnRS;  // TODO how do I handle this problem?
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

    public StudentTableModel getStudentTableModel() {
        return studentTableModel;
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

    public void setStudentTableModel(StudentTableModel studentTableModel) {
        this.studentTableModel = studentTableModel;
    }
}
