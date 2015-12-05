package com.margaret;

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
    Double classPrice;
    ArrayList<MusicClass> musicClasses;

    public MusicClass(){
        musicClasses = new ArrayList<>();
    }

    public ResultSet AllDataQuery(){
        ResultSet returnRS = null;

        try {

            String allDataQuery = "SELECT * FROM " + CreateTables.CLASS_TABLE_NAME;
            returnRS = ConnectDB.statement.executeQuery(allDataQuery);
            return returnRS;
        }
        catch (SQLException sqle){
            System.out.println("in Music class All Data Query call to select all " + sqle);
            return returnRS;  // TODO how do I handle this problem
        }
    }

    public void DisplayAllClasses (ResultSet rs){
        Scanner s = new Scanner(System.in);

        ArrayList<String> classNamesARL = new ArrayList<String>();
        ArrayList<String> classDaysARL = new ArrayList<>();
        ArrayList<String> classTimesARL = new ArrayList<String>();
        ArrayList<Double> classPriceARL = new ArrayList<Double>();
        int rowCount = GetRowCount(rs);
        try {
            while (rs.next()) {
                System.out.println("record in result set is " + rs.getString(CreateTables.CLASS_NAME_COLUMN) + " " + rs.getString(CreateTables.CLASS_DAY_COLUMN) + " " + rs.getString(CreateTables.CLASS_PRICE_COLUMN));
                classNamesARL.add(rs.getString(CreateTables.CLASS_NAME_COLUMN));
                classTimesARL.add(rs.getString(CreateTables.CLASS_TIME_COLUMN));
                classDaysARL.add(rs.getString(CreateTables.CLASS_DAY_COLUMN));
                classPriceARL.add(rs.getDouble(CreateTables.CLASS_PRICE_COLUMN));
            }
            rs.beforeFirst();
            if (rs.next() == false) {
                System.out.println("Nothing matches that solver entry.");
            } else { // then output the results once you know there is a result set
                int loopCount = 0;  // have to count again
                System.out.println("Enter the number class you wish to take:");
                for (int i = 0; i < rowCount; i++) {
                    loopCount++;
                    System.out.println(loopCount + ". " + classNamesARL.get(i) + ", " + classDaysARL.get(i) + " at " + classTimesARL.get(i));
                }
                String classToJoinStr = s.nextLine();
                int classToJoin = Integer.parseInt(classToJoinStr);
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
}

