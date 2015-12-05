package com.margaret;

import javax.swing.table.AbstractTableModel;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Margaret on 12/5/2015.
 */
public class StudentTableModel extends AbstractTableModel {

    private int rowCount = 0;
    private int colCount = 0;
    private ResultSet studentsRS;

    public StudentTableModel (ResultSet rs){
        this.studentsRS = rs;
    }

    private void setup(){

        countRows();

        try{
            colCount = studentsRS.getMetaData().getColumnCount();

        } catch (SQLException se) {
            System.out.println("Error counting columns" + se);
        }

    }

    public void updateResultSet(ResultSet newRS){
        studentsRS = newRS;
        setup();
    }

    //Delete row, return true if successful, false otherwise
    public boolean deleteRow(int row){
        try {
            studentsRS.absolute(row + 1);
            studentsRS.deleteRow();
            //Tell table to redraw itself
            fireTableDataChanged();
            return true;
        }catch (SQLException se) {
            System.out.println("Delete row error " + se);
            return false;
        }
    }

    //returns true if successful, false if error occurs
    public boolean insertRow(String FirstName, String LastName, String Phone) {

        try {
            //Move to insert row, insert the appropriate data in each column, insert the row, move cursor back to where it was before we started
            studentsRS.moveToInsertRow();
            studentsRS.updateString(CreateTables.STUDENT_FIRST_COLUMN, FirstName);
            studentsRS.updateString(CreateTables.STUDENT_LAST_COLUMN, LastName);
            studentsRS.updateString(CreateTables.STUDENT_PHONE_COLUMN, Phone);
            studentsRS.insertRow();
            studentsRS.moveToCurrentRow();
            fireTableDataChanged();
            return true;

        } catch (SQLException e) {
            System.out.println("Error adding row");
            System.out.println(e);
            return false;
        }

    }


    @Override
    public int getRowCount() {
        countRows();
        return rowCount;
    }
    private void countRows() {
        rowCount = 0;
        try {
            //Move cursor to the start...
            studentsRS.beforeFirst();
            // next() method moves the cursor forward one row and returns true if there is another row ahead
            while (studentsRS.next()) {
                rowCount++;

            }
            studentsRS.beforeFirst();

        } catch (SQLException se) {
            System.out.println("Error counting rows " + se);
        }

    }
    @Override
    public int getColumnCount() {
        return 0;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        try{
            //  System.out.println("get value at, row = " +row);
            studentsRS.absolute(rowIndex+1);
            Object o = studentsRS.getObject(columnIndex+1);
            return o.toString();
        }catch (SQLException se) {
            System.out.println(se);
            //se.printStackTrace();
            return se.toString();

        }
    }
}
