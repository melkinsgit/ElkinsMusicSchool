package com.margaret;

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

    public Teacher (String first, String last, String phone){
        this.firstName = first;
        this.lastName = last;
        this.phone = phone;
    }


    public void AddTeacher (String First, String Last, String Phone){

        try {

            String addDataSQL = "INSERT INTO " + CreateTables.TEACHER_TABLE_NAME + "(" + CreateTables.TEACHER_FIRST_COLUMN + ", " + CreateTables.TEACHER_LAST_COLUMN + ", " + CreateTables.TEACHER_PHONE_COLUMN + ")" + " VALUES ('" + First + "', '" + Last+ "', '" + Phone + "')";
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

            String allDataQuery = "SELECT * FROM " + CreateTables.TEACHER_TABLE_NAME;
            returnRS = ConnectDB.statement.executeQuery(allDataQuery);
            return returnRS;
        }
        catch (SQLException sqle){
            System.out.println("in Teacher All Data Query call to select all " + sqle);
            return returnRS;  // TODO how do I handle this problem?
        }
    }

    public void DisplayAllTeachers (ResultSet rs){
        Scanner s = new Scanner(System.in);

        ArrayList<String> TeacherFirstARL = new ArrayList<String>();
        ArrayList<String> TeacherLastARL = new ArrayList<>();
        ArrayList<Integer> TeacherIDARL = new ArrayList<Integer>();
        int rowCount = GetRowCount(rs);
        try {
            while (rs.next()) {
                System.out.println("record in result set is " + rs.getString(CreateTables.STUDENT_FIRST_COLUMN) + " " + rs.getString(CreateTables.STUDENT_LAST_COLUMN));
                TeacherFirstARL.add(rs.getString(CreateTables.TEACHER_FIRST_COLUMN));
                TeacherLastARL.add(rs.getString(CreateTables.TEACHER_LAST_COLUMN));
                TeacherIDARL.add(rs.getInt(CreateTables.TEACHER_PK_COL));
            }
            rs.beforeFirst();


            if (rs.next() == false) {
                System.out.println("There are no teachers in the Database.");
            } else { // then output the results once you know there is a result set
                int loopCount = 0;  // have to count again
                System.out.println("These are the teachers in the school:");
                for (int i = 0; i < rowCount; i++) {
                    loopCount++;
                    System.out.println(TeacherIDARL.get(i) + ". " + TeacherFirstARL.get(i) + " " + TeacherLastARL.get(i));
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
