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

    public void AddStudent (Student studentToAdd){

        try {

            String addDataSQL = "INSERT INTO " + CreateTables.STUDENT_TABLE_NAME + "(" + CreateTables.STUDENT_FIRST_COLUMN + ", " + CreateTables.STUDENT_LAST_COLUMN + ", " + CreateTables.STUDENT_PHONE_COLUMN + ")" + " VALUES ( ?, ?, ?)";
//            ConnectDB.statement.executeUpdate(addDataSQL);
            System.out.println(addDataSQL);
            PreparedStatement addToSt = ConnectDB.conn.prepareStatement(addDataSQL);
            addToSt.setString(1, studentToAdd.firstName);
            addToSt.setString(2, studentToAdd.lastName);
            addToSt.setString(3, studentToAdd.phone);
            System.out.println(addToSt);
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
        }
        return returnRS;
    }

//    public int DisplayAllStudents (ResultSet rs){
//
//        Scanner s = new Scanner(System.in);
//
//        ArrayList<String> StudentFirstARL = new ArrayList<String>();
//        ArrayList<String> StudentLastARL = new ArrayList<>();
//        ArrayList<Integer> StudentIDARL = new ArrayList<Integer>();
//        ArrayList<Double> classPriceARL = new ArrayList<Double>();
//        int rowCount = Queries.GetRowCount(rs);
//        try {
//            while (rs.next()) {
//                System.out.println("record in result set is " + rs.getString(CreateTables.STUDENT_FIRST_COLUMN) + " " + rs.getString(CreateTables.STUDENT_LAST_COLUMN));
//                StudentFirstARL.add(rs.getString(CreateTables.STUDENT_FIRST_COLUMN));
//                StudentLastARL.add(rs.getString(CreateTables.STUDENT_LAST_COLUMN));
//                StudentIDARL.add(rs.getInt(CreateTables.STUDENT_PK_COL));
//            }
//            rs.beforeFirst();
//
//            if (rs.next() == false) {
//                System.out.println("There are no students in the Database.");
//            } else { // then output the results once you know there is a result set
//                int loopCount = 0;  // have to count again
//                System.out.println("These are the students in the school:");
//                for (int i = 0; i < rowCount; i++) {
//                    loopCount++;
//                    System.out.println(StudentIDARL.get(i) + ". " + StudentFirstARL.get(i) + " " + StudentLastARL.get(i));
//                }
//                String studentPickedStr = s.nextLine();
//                int studentPicked = Integer.parseInt(studentPickedStr);
//                rs.beforeFirst();
//                return studentPicked;
//            }
//        }
//        catch (SQLException sqle){
//            System.out.println("In music class Display all Classes " + sqle);
//        }
//        return -1;
//    }

    public ResultSet GetSchedule(String studentPicked) {

        System.out.println( "The parameter sent to Class to Join is " + studentPicked);
        ResultSet rs1;
        try {
//            String findClassData = "SELECT * FROM " + CreateTables.STUDENT_TABLE_NAME + " WHERE " + CreateTables.STUDENT_PK_COL + " = ? ";
//            PreparedStatement selectRowByID = ConnectDB.conn.prepareStatement(findClassData);
//            selectRowByID.setInt(1, studentPicked);
//            rs1 = selectRowByID.executeQuery();
//            rs1.next();
//            System.out.println("student sked to show is " + rs1.getString(CreateTables.STUDENT_FIRST_COLUMN) + " " + rs1.getString(CreateTables.STUDENT_LAST_COLUMN) + " " + rs1.getInt(CreateTables.STUDENT_PK_COL));
////            rs1.beforeFirst();

            // TODO make this beast a global
//            String selectSked = "Select " + CreateTables.STUDENT_FIRST_COLUMN + " as StudentFirstName, " + CreateTables.STUDENT_LAST_COLUMN + " as StudentLastName, " + CreateTables.CLASS_NAME_COLUMN + " as MusicClass, " + CreateTables.CLASS_DAY_COLUMN + " as DayofClass, "+ CreateTables.CLASS_TIME_COLUMN + " as MeetingTime FROM " + CreateTables.STUDENT_TABLE_NAME + " JOIN " + CreateTables.STUDENT_CLASS_TABLE_NAME + " ON " + CreateTables.STUDENT_CLASS_TABLE_NAME + "." + CreateTables.STUDENT_CLASS_ID + " = " + CreateTables.STUDENT_CLASS_TABLE_NAME + "." + CreateTables.STUD_FK_PK_COL + " JOIN " + CreateTables.CLASS_TABLE_NAME + " ON " + CreateTables.STUDENT_CLASS_TABLE_NAME + "." + CreateTables.CLASS_FK_PK_COL + " = " + CreateTables.CLASS_TABLE_NAME + "." + CreateTables.CLASS_FK_PK_COL;
//            System.out.println(selectSked);

//            select students.FirstName as StudentFirstName, students.LastName as StudentLastName, classes.ClassName as MusicClass, classes.DayOfWeek as DayofClass, classes.TimeOfDay as MeetingTime
//            from students
//            join studentclass on students.StudentID = studentclass.StudentID
//            join classes on studentclass.ClassID = classes.ClassID
//            order by StudentLastName;

            //  TODO then use this query
//            select MusicClass, DayofClass, MeetingTime from (BIG HUGE QUERY) as Skeds
//            where StudentLastName like 'Elkins';
//            System.out.println(Queries.joinQuery);
            String studentPickedFirst = studentPicked.substring(0, studentPicked.indexOf(" "));
            String studentPickedLast = studentPicked.substring(studentPicked.indexOf(" ")+1);
            System.out.println("First and last name are " + studentPickedFirst + " and " + studentPickedLast);

            String studentSked = "Select " + CreateTables.CLASS_NAME_COLUMN + ", " + CreateTables.CLASS_DAY_COLUMN + ", " + CreateTables.CLASS_TIME_COLUMN + " from " + Queries.simplerJoin + " where " + CreateTables.STUDENT_LAST_COLUMN + " like '" + studentPickedLast + "' and " + CreateTables.STUDENT_FIRST_COLUMN + " like '" + studentPickedFirst + "'";
            System.out.println(studentSked);
            rs1 = ConnectDB.statement.executeQuery(studentSked);

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
//            if (!rs.next()) {
//                System.out.println("Displaying empty!");
//            }
//            rs.beforeFirst();

            while (rs.next()) {
//                System.out.println("record in result set is " + rs.getString(CreateTables.STUDENT_FIRST_COLUMN) + " " + rs.getString(CreateTables.STUDENT_LAST_COLUMN));
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
