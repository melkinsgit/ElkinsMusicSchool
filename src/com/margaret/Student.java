package com.margaret;

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

    public Student (String first, String last, String phone){
        this.firstName = first;
        this.lastName = last;
        this.phone = phone;
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

    public static ResultSet AllDataQuery(){
        ResultSet returnRS = null;

        try {

            String allDataQuery = "SELECT * FROM " + CreateTables.STUDENT_TABLE_NAME;
            returnRS = ConnectDB.statement.executeQuery(allDataQuery);

//            if (studentTableModel == null) {
//                //If no current movieDataModel, then make one
//                studentTableModel = new StudentTableModel(returnRS);
//            } else {
//                //Or, if one already exists, update its ResultSet
//                studentTableModel.updateResultSet(returnRS);
//            }
            return returnRS;
        }
        catch (SQLException sqle){
            System.out.println("in Student All Data Query call to select all " + sqle);
            return returnRS;  // TODO how do I handle this problem?
        }
    }

    public void DisplayAllStudents (ResultSet rs){
        Scanner s = new Scanner(System.in);

        ArrayList<String> StudentFirstARL = new ArrayList<String>();
        ArrayList<String> StudentLastARL = new ArrayList<>();
        ArrayList<Integer> StudentIDARL = new ArrayList<Integer>();
        ArrayList<Double> classPriceARL = new ArrayList<Double>();
        int rowCount = GetRowCount(rs);
        try {
            while (rs.next()) {
                System.out.println("record in result set is " + rs.getString(CreateTables.STUDENT_FIRST_COLUMN) + " " + rs.getString(CreateTables.STUDENT_LAST_COLUMN));
                StudentFirstARL.add(rs.getString(CreateTables.STUDENT_FIRST_COLUMN));
                StudentLastARL.add(rs.getString(CreateTables.STUDENT_LAST_COLUMN));
                StudentIDARL.add(rs.getInt(CreateTables.STUDENT_PK_COL));
            }
            rs.beforeFirst();


            if (rs.next() == false) {
                System.out.println("There are no students in the Database.");
            } else { // then output the results once you know there is a result set
                int loopCount = 0;  // have to count again
                System.out.println("These are the students in the school:");
                for (int i = 0; i < rowCount; i++) {
                    loopCount++;
                    System.out.println(StudentIDARL.get(i) + ". " + StudentFirstARL.get(i) + " " + StudentLastARL.get(i));
                }
                String studentPickedStr = s.nextLine();
                int studentPicked = Integer.parseInt(studentPickedStr);
                rs.beforeFirst();
            }
        }
        catch (SQLException sqle){
            System.out.println("In music class Display all Classes " + sqle);
        }
    }

    private int GetRowCount(ResultSet resultSet){

        int rowCount = 0;
        System.out.println("in row count");
        try {
            //Move cursor to the start...
            resultSet.beforeFirst();
            // next() method moves the cursor forward one row and returns true if there is another row ahead
            while (resultSet.next()) {
                rowCount++;
            }
            resultSet.beforeFirst();

        } catch (SQLException se) {
            System.out.println("Error counting rows " + se);
        }
        System.out.println("About to return " + rowCount + " as the number of rows in Classes table.");
        return rowCount;
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
