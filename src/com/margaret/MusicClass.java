package com.margaret;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by sn0173nd on 12/4/2015.
 */
public class MusicClass {

    String className;
    String classTime;
    String classDay;
    String classPrice;
    Integer classTeacher;

    public MusicClass(){
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

    public void EnrollInClass(String classToJoin, String studentPicked) {
        ResultSet rs1;
        ResultSet rs2;
        try {
            String classIDPicked = classToJoin.substring(0, classToJoin.indexOf(" "));
            String studentIDPicked = studentPicked.substring(0, studentPicked.indexOf(" "));

            int classJoinInt = Integer.parseInt(classIDPicked);
            int studentJointInt = Integer.parseInt(studentIDPicked);

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


}

