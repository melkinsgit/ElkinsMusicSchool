package com.margaret;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by sn0173nd on 12/4/2015.
 */
public class MusicClass {

    String className;
    String classTime;
    String classDay;
    String classPrice;
    Integer classTeacher;

    ArrayList<MusicClass> musicClasses;

    public MusicClass(){
//        /musicClasses = new ArrayList<>();
    }

    public static ResultSet AllDataQuery(){
        ResultSet returnRS = null;

        try {

            String allDataQuery = "SELECT * FROM " + CreateTables.CLASS_TABLE_NAME;
            returnRS = ConnectDB.statement.executeQuery(allDataQuery);
            return returnRS;
        }
        catch (SQLException sqle){
            return returnRS;
        }
    }

    public int DisplayAllClasses (ResultSet rs){
        Scanner s = new Scanner(System.in);

        ArrayList<Integer> classIDsARL = new ArrayList<>();
        ArrayList<String> classNamesARL = new ArrayList<String>();
        ArrayList<String> classDaysARL = new ArrayList<>();
        ArrayList<String> classTimesARL = new ArrayList<String>();
        ArrayList<Double> classPriceARL = new ArrayList<Double>();
        int rowCount = Queries.GetRowCount(rs);
        try {
            while (rs.next()) {
                classIDsARL.add(rs.getInt(CreateTables.CLASS_PK_COL));
                classNamesARL.add(rs.getString(CreateTables.CLASS_NAME_COLUMN));
                classTimesARL.add(rs.getString(CreateTables.CLASS_TIME_COLUMN));
                classDaysARL.add(rs.getString(CreateTables.CLASS_DAY_COLUMN));
                classPriceARL.add(rs.getDouble(CreateTables.CLASS_PRICE_COLUMN));
            }
            rs.beforeFirst();
            if (rs.next() == false) {
                System.out.println("There are no classes in the Database.");
            } else { // then output the results once you know there is a result set
                int loopCount = 0;  // have to count again
                System.out.println("Enter the number class you wish to take:");
                for (int i = 0; i < rowCount; i++) {
                    loopCount++;
                    System.out.println(classIDsARL.get(i) + ". " + classNamesARL.get(i) + ", " + classDaysARL.get(i) + " at " + classTimesARL.get(i));
                }
                String classToJoinStr = s.nextLine();
                int classToJoin = Integer.parseInt(classToJoinStr);
                rs.beforeFirst();
                return classToJoin;
            }
        }
        catch (SQLException sqle){
            System.out.println("In music class Display all Classes " + sqle);
        }
        return -1;
    }

    public void EnrollInClass(String classToJoin, String studentPicked) {
        System.out.println("Class to join is " + classToJoin + " and student to join is " + studentPicked);
        ResultSet rs1;
        ResultSet rs2;
        try {
            String classIDPicked = classToJoin.substring(0, classToJoin.indexOf(" "));
            System.out.println("Class ID for search is " + classIDPicked);

            String findClassData = "SELECT * FROM " + CreateTables.CLASS_TABLE_NAME + " WHERE " + CreateTables.CLASS_PK_COL + " LIKE ? ";
            PreparedStatement selectRowByID = ConnectDB.conn.prepareStatement(findClassData);
            selectRowByID.setString(1, classIDPicked);
            rs1 = selectRowByID.executeQuery();

            String studentIDPicked = studentPicked.substring(0, studentPicked.indexOf(" "));
            System.out.println("Student ID for search is " + studentIDPicked);

            String findStudentData = "SELECT * FROM " + CreateTables.STUDENT_TABLE_NAME + " WHERE " + CreateTables.STUDENT_PK_COL + " LIKE ? ";
            PreparedStatement selectRowByLastName = ConnectDB.conn.prepareStatement(findStudentData);
            selectRowByLastName.setString(1, studentIDPicked);
            rs2 = selectRowByLastName.executeQuery();

            int classJoinInt = Integer.parseInt(classIDPicked);
            int studentJointInt = Integer.parseInt(studentIDPicked);

            while (rs1.next()){
                String foundClassID = rs1.getString(CreateTables.CLASS_PK_COL);
                String foundClassName = rs1.getString(CreateTables.CLASS_NAME_COLUMN);
                String foundClassTeacherID = rs1.getString(CreateTables.TEACHER_PK_FK);
                System.out.println("In Class to Enroll In the data is " + foundClassID + " " + foundClassName + " " + foundClassTeacherID);
            }

            String studentClassInsert = "INSERT " + CreateTables.STUDENT_CLASS_TABLE_NAME + "(" + CreateTables.STUD_FK_PK_COL + ", " + CreateTables.CLASS_FK_PK_COL + ") VALUES ( ?, ? )";
            PreparedStatement insertToStCl = ConnectDB.conn.prepareStatement(studentClassInsert);
            insertToStCl.setInt(1, studentJointInt);
            insertToStCl.setInt(2, classJoinInt);
            insertToStCl.executeUpdate();
            Queries.studentEnrolled = true;
        }
        catch (SQLException sqle){
            if (sqle.getMessage().substring(0, sqle.getMessage().indexOf(" ")).equals("Duplicate")){
                Queries.duplicateFlag = true;
            }
            System.out.println("In Enroll In Class " + sqle.getMessage() + sqle);
        }
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public void setClassTime(String classTime) {
        this.classTime = classTime;
    }

    public void setClassDay(String classDay) {
        this.classDay = classDay;
    }

    public void setClassPrice(String classPrice) {
        this.classPrice = classPrice;
    }

    public void setClassTeacher(Integer classTeacher) {
        this.classTeacher = classTeacher;
    }

    public void AddClass() {
        try {

            String addDataSQL = "INSERT INTO " + CreateTables.CLASS_TABLE_NAME + "(" + CreateTables.CLASS_NAME_COLUMN + ", " + CreateTables.CLASS_DAY_COLUMN + ", " + CreateTables.CLASS_TIME_COLUMN + ", " + CreateTables.CLASS_PRICE_COLUMN + ", " + CreateTables.TEACHER_PK_COL + ")" + " VALUES ( ?, ?, ?, ?, ?)";
            PreparedStatement addToSt = ConnectDB.conn.prepareStatement(addDataSQL);
            addToSt.setString(1, this.className);
            addToSt.setString(2, this.classDay);
            addToSt.setString(3, this.classTime);
            addToSt.setString(4, this.classPrice);
            addToSt.setInt(5, this.classTeacher);
            addToSt.executeUpdate();
        }
        catch (SQLException se) {
            System.out.println("In Student Class Add Student Method " + se);
            se.printStackTrace();
        }
    }
}

