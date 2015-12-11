package com.margaret;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Margaret on 12/6/2015.
 */
public class Queries {

    public final static int PHONE_LENGTH = 12;
//    public static ResultSet studentComboBox = Student.AllDataQuery();
//    public static ResultSet classComboBox = MusicClass.AllDataQuery();

//    public final static String joinQuery = "Select " + CreateTables.STUDENT_FIRST_COLUMN + " as StudentFirstName, " + CreateTables.STUDENT_LAST_COLUMN + " as StudentLastName, " + CreateTables.CLASS_NAME_COLUMN + " as MusicClass, " + CreateTables.CLASS_DAY_COLUMN + " as DayofClass, "+ CreateTables.CLASS_TIME_COLUMN + " as MeetingTime FROM " + CreateTables.STUDENT_TABLE_NAME + " JOIN " + CreateTables.STUDENT_CLASS_TABLE_NAME + " ON " + CreateTables.STUDENT_CLASS_TABLE_NAME + "." + CreateTables.STUDENT_CLASS_ID + " = " + CreateTables.STUDENT_CLASS_TABLE_NAME + "." + CreateTables.STUD_FK_PK_COL + " JOIN " + CreateTables.CLASS_TABLE_NAME + " ON " + CreateTables.STUDENT_CLASS_TABLE_NAME + "." + CreateTables.CLASS_FK_PK_COL + " = " + CreateTables.CLASS_TABLE_NAME + "." + CreateTables.CLASS_FK_PK_COL;

//    public final static String simpleJoin = "Select * FROM Students INNER JOIN StudentClass ON Students.StudentID = StudentClass.StudentID INNER JOIN Classes ON StudentClass.ClassID = Classes.ClassID where Students.LastName like 'Elkins'";

    public final static String simplerJoin = CreateTables.STUDENT_TABLE_NAME + " inner join " + CreateTables.STUDENT_CLASS_TABLE_NAME + " on " + CreateTables.STUDENT_TABLE_NAME + "." + CreateTables.STUDENT_PK_COL + " = " + CreateTables.STUDENT_CLASS_TABLE_NAME + "." + CreateTables.STUD_FK_PK_COL + " inner join " + CreateTables.CLASS_TABLE_NAME + " on " + CreateTables.STUDENT_CLASS_TABLE_NAME + "." + CreateTables.CLASS_FK_PK_COL + " = " + CreateTables.CLASS_TABLE_NAME + "." + CreateTables.CLASS_FK_PK_COL;

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
