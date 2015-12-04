package com.margaret;

import java.sql.ResultSet;
import java.sql.SQLException;

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
