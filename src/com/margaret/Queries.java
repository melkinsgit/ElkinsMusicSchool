package com.margaret;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Margaret on 12/6/2015.
 */
public class Queries {

    public final static String joinQuery = "Select " + CreateTables.STUDENT_FIRST_COLUMN + " as StudentFirstName, " + CreateTables.STUDENT_LAST_COLUMN + " as StudentLastName, " + CreateTables.CLASS_NAME_COLUMN + " as MusicClass, " + CreateTables.CLASS_DAY_COLUMN + " as DayofClass, "+ CreateTables.CLASS_TIME_COLUMN + " as MeetingTime FROM " + CreateTables.STUDENT_TABLE_NAME + " JOIN " + CreateTables.STUDENT_CLASS_TABLE_NAME + " ON " + CreateTables.STUDENT_CLASS_TABLE_NAME + "." + CreateTables.STUDENT_CLASS_ID + " = " + CreateTables.STUDENT_CLASS_TABLE_NAME + "." + CreateTables.STUD_FK_PK_COL + " JOIN " + CreateTables.CLASS_TABLE_NAME + " ON " + CreateTables.STUDENT_CLASS_TABLE_NAME + "." + CreateTables.CLASS_FK_PK_COL + " = " + CreateTables.CLASS_TABLE_NAME + "." + CreateTables.CLASS_FK_PK_COL;

    public final static String simpleJoin = "Select * FROM Students INNER JOIN StudentClass ON Students.StudentID = StudentClass.StudentID INNER JOIN Classes ON StudentClass.ClassID = Classes.ClassID where Students.LastName like 'Elkins'";

    public final static String simplerJoin = CreateTables.STUDENT_TABLE_NAME + " inner join " + CreateTables.STUDENT_CLASS_TABLE_NAME + " on " + CreateTables.STUDENT_TABLE_NAME + "." + CreateTables.STUDENT_PK_COL + " = " + CreateTables.STUDENT_CLASS_TABLE_NAME + "." + CreateTables.STUD_FK_PK_COL + " inner join " + CreateTables.CLASS_TABLE_NAME + " on " + CreateTables.STUDENT_CLASS_TABLE_NAME + "." + CreateTables.CLASS_FK_PK_COL + " = " + CreateTables.CLASS_TABLE_NAME + "." + CreateTables.CLASS_FK_PK_COL;

    public static int GetRowCount(ResultSet resultSet){

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
        System.out.println("About to return " + rowCount + " as the number of rows in result set.");
        return rowCount;
    }

    /*
    Select ClassName, DayOfWeek, TimeOfDay from Students inner join StudentClass on Students.StudentID = StudentClass.StudentID inner join Classes on StudentClass.ClassID = Classes.ClassID where LastName like 'Elkins'
     */
}
