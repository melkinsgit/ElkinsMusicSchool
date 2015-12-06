package com.margaret;

/**
 * Created by Margaret on 12/6/2015.
 */
public class Queries {

    public final static String joinQuery = "Select " + CreateTables.STUDENT_FIRST_COLUMN + " as StudentFirstName, " + CreateTables.STUDENT_LAST_COLUMN + " as StudentLastName, " + CreateTables.CLASS_NAME_COLUMN + " as MusicClass, " + CreateTables.CLASS_DAY_COLUMN + " as DayofClass, "+ CreateTables.CLASS_TIME_COLUMN + " as MeetingTime FROM " + CreateTables.STUDENT_TABLE_NAME + " JOIN " + CreateTables.STUDENT_CLASS_TABLE_NAME + " ON " + CreateTables.STUDENT_CLASS_TABLE_NAME + "." + CreateTables.STUDENT_CLASS_ID + " = " + CreateTables.STUDENT_CLASS_TABLE_NAME + "." + CreateTables.STUD_FK_PK_COL + " JOIN " + CreateTables.CLASS_TABLE_NAME + " ON " + CreateTables.STUDENT_CLASS_TABLE_NAME + "." + CreateTables.CLASS_FK_PK_COL + " = " + CreateTables.CLASS_TABLE_NAME + "." + CreateTables.CLASS_FK_PK_COL;
}
