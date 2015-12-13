package com.margaret;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Margaret on 12/6/2015.
 */
public class Queries {

    public final static int PHONE_LENGTH = 12;


    //Select * from Classes inner join Teachers on Classes.TeacherID = Teachers.TeacherID

    public final static String teacherSkedJoin = "SELECT * FROM " + CreateTables.CLASS_TABLE_NAME + " INNER JOIN " + CreateTables.TEACHER_TABLE_NAME + " ON " + CreateTables.CLASS_TABLE_NAME + "." + CreateTables.TEACHER_PK_COL + " = " + CreateTables.TEACHER_TABLE_NAME + "." + CreateTables.TEACHER_PK_COL;

    public final static String enrollJoin = "SELECT * FROM " + CreateTables.CLASS_TABLE_NAME + " INNER JOIN " + CreateTables.TEACHER_TABLE_NAME + " ON " + CreateTables.CLASS_TABLE_NAME + "." + CreateTables.TEACHER_PK_COL + "=" + CreateTables.TEACHER_TABLE_NAME + "." + CreateTables.TEACHER_PK_COL;

    public final static String studentSkedJoin = CreateTables.STUDENT_TABLE_NAME + " inner join " + CreateTables.STUDENT_CLASS_TABLE_NAME + " on " + CreateTables.STUDENT_TABLE_NAME + "." + CreateTables.STUDENT_PK_COL + " = " + CreateTables.STUDENT_CLASS_TABLE_NAME + "." + CreateTables.STUD_FK_PK_COL + " inner join " + CreateTables.CLASS_TABLE_NAME + " on " + CreateTables.STUDENT_CLASS_TABLE_NAME + "." + CreateTables.CLASS_FK_PK_COL + " = " + CreateTables.CLASS_TABLE_NAME + "." + CreateTables.CLASS_FK_PK_COL;

    public static boolean duplicateFlag = false;

    public static boolean studentEnrolled = false;

    // From Clara's Movie Rating project
    public static int GetRowCount(ResultSet resultSet){

        int rowCount = 0;
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
        return rowCount;
    }

    public static boolean IsValidDBString (String DBEntry){
        if (DBEntry.equals("")){
            return false;
        }
        else { return true; }
    }

    public static String phoneInputMsg (String DBPhone){
        String retString = "";
        if (DBPhone.length() != PHONE_LENGTH){
            retString = "That phone number is not 12 characters. Please re-enter a phone number and click Add This Student.";
        }
        // TODO see about getting dashes in the right places
        return retString;
    }

    /*
    Select ClassName, DayOfWeek, TimeOfDay from Students inner join StudentClass on Students.StudentID = StudentClass.StudentID inner join Classes on StudentClass.ClassID = Classes.ClassID where LastName like 'Elkins'
     */
}
