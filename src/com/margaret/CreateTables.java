package com.margaret;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by sn0173nd on 12/2/2015. This class was taken from code Clara provided. I included prepared statements in the methods that add data to the db tables.
 */
public class CreateTables {

    // Student table name, pk and columns
    public final static String STUDENT_TABLE_NAME = "Students";
    public final static String STUDENT_PK_COL = "StudentID";
    public final static String STUDENT_FIRST_COLUMN = "FirstName";
    public final static String STUDENT_LAST_COLUMN = "LastName";
    public final static String STUDENT_PHONE_COLUMN = "Phone";

    // Teacher table name, pk and columns
    public final static String TEACHER_TABLE_NAME = "Teachers";
    public final static String TEACHER_PK_COL = "TeacherID";
    public final static String TEACHER_FIRST_COLUMN = "FirstName";
    public final static String TEACHER_LAST_COLUMN = "LastName";
    public final static String TEACHER_PHONE_COLUMN = "Phone";

    // Class table name, pk and columns
    public final static String CLASS_TABLE_NAME = "Classes";
    public final static String CLASS_PK_COL = "ClassID";
    public final static String CLASS_NAME_COLUMN = "ClassName";
    public final static String CLASS_DAY_COLUMN = "DayOfWeek";
    public final static String CLASS_TIME_COLUMN = "TimeOfDay";
    public final static String CLASS_PRICE_COLUMN = "Price";
    public final static String TEACHER_PK_FK = "TeacherID";

    // StudentClass table name, pks and columns
    public final static String STUDENT_CLASS_TABLE_NAME = "StudentClass";
    public final static String STUDENT_CLASS_ID = "StudClassID";
    public final static String STUD_FK_PK_COL = "StudentID";
    public final static String CLASS_FK_PK_COL = "ClassID";

    public CreateTables(){
        StartTables();
    }

    public void StartTables() {
        try {
            if (!studentTableExists()) {

                //Create a student table in the database with three columns, first name, last name and phone. There is also a primary key column that auto increments. It is the unique identifier for each record.
                String createTableSQL = "CREATE TABLE " + STUDENT_TABLE_NAME + " (" + STUDENT_PK_COL + " int NOT NULL AUTO_INCREMENT PRIMARY KEY, " + STUDENT_FIRST_COLUMN + " varchar(50), " + STUDENT_LAST_COLUMN + " varchar(50), " + STUDENT_PHONE_COLUMN + " varchar(12)" + ")";
                ConnectDB.statement.executeUpdate(createTableSQL);
                AddToStudent();  // puts test data in the student table
            }

            if (!teacherTableExists()) {

                //Create a teacher table in the database with three columns, first name, last name and phone. There is also a primary key column that auto increments. It is the unique identifier for each record.
                String createTableSQL2 = "CREATE TABLE " + TEACHER_TABLE_NAME + " (" + TEACHER_PK_COL + " int NOT NULL AUTO_INCREMENT PRIMARY KEY, " + TEACHER_FIRST_COLUMN + " varchar(50), " + TEACHER_LAST_COLUMN + " varchar(50), " + TEACHER_PHONE_COLUMN + " varchar(50))";
                ConnectDB.statement.executeUpdate(createTableSQL2);
                AddToTeacher();  // puts test data in the teacher table
            }

            if (!classTableExists()) {

                //Create a class table in the database with four columns, class name, day, time and price. There is also a primary key column that auto increments. It is the unique identifier for each record. And there is a foreign key, which is the primary key from the teacher column. This table is used to connect the teacher and the class tables in a one to many realationship.
                String createTableSQL2 = "CREATE TABLE " + CLASS_TABLE_NAME + " (" + CLASS_PK_COL + " int NOT NULL AUTO_INCREMENT PRIMARY KEY, " + CLASS_NAME_COLUMN + " varchar(50), " + CLASS_DAY_COLUMN + " varchar(50), " + CLASS_TIME_COLUMN + " varchar (50), " + CLASS_PRICE_COLUMN + " DECIMAL (5,2), " + TEACHER_PK_FK + " INT)";
                ConnectDB.statement.executeUpdate(createTableSQL2);
                AddToClass();  // puts test data in the class table
                MakeTeacherFKinClass();  // adds foreign key constraint that connects the teacher and class tables

            }

            if (!studentClassTableExists()){
                //Create a StudentClass in the database with two columns, the primary keys from the Student and Class tables. Togeter they comprise a primary key. This table is used to connect the student and the class tables in a many to many realationship.
                String createTableSQL2 = "CREATE TABLE " + STUDENT_CLASS_TABLE_NAME + " (" + STUD_FK_PK_COL + " int NOT NULL, " + CLASS_FK_PK_COL + " int NOT NULL, PRIMARY KEY (" + STUD_FK_PK_COL + ", " + CLASS_FK_PK_COL + "))";
                ConnectDB.statement.executeUpdate(createTableSQL2);
                AddToStudentClass();  // puts test data in StudentClass table
            }
        }
        catch (SQLException sqle){
            System.out.println("In Create Tables Class Start Tables Mehtod " + sqle);
        }
    }


    public void MakeTeacherFKinClass () {

        try {

            String constraintName = "teacherFKConst";
            String fkConstraint = "ALTER TABLE " + CLASS_TABLE_NAME + " MODIFY COLUMN " + TEACHER_PK_COL + " INT NOT NULL, ADD CONSTRAINT " + constraintName + " FOREIGN KEY(" + TEACHER_PK_FK + ") REFERENCES " + TEACHER_TABLE_NAME + "(" + TEACHER_PK_COL + ");";
            ConnectDB.statement.executeUpdate(fkConstraint);

        } catch(SQLException se){
            System.out.println("Setting contraint for foreign key in Class Table " + se);
            se.printStackTrace();
        }
    }

    // Add some test data
    // For the AddToStudent, AddToTeach and AddToClass we have to specify which columns the data will go into, because we want to omit the ID column and have MySQL fill it in for us. Going forward, data is added to these tables when the user chooses the option to do so in teh GUI.
    // For AddToStudentClass we insert key values that could be true. Going forward, values are added to StudentClass any time a student enrolls in a class.
    public void AddToStudent(){

        try {

            String addDataSQL = "INSERT INTO " + STUDENT_TABLE_NAME + "(" + STUDENT_FIRST_COLUMN + ", " + STUDENT_LAST_COLUMN + ", " + STUDENT_PHONE_COLUMN + ")" + " VALUES ('Margaret', 'Elkins', '555-555-1212')";
            ConnectDB.statement.executeUpdate(addDataSQL);
            addDataSQL = "INSERT INTO " + STUDENT_TABLE_NAME + "(" + STUDENT_FIRST_COLUMN + ", " + STUDENT_LAST_COLUMN +", " + STUDENT_PHONE_COLUMN + ")" + " VALUES ('Scott', 'Sivad', '555-555-1313')";
            ConnectDB.statement.executeUpdate(addDataSQL);
            addDataSQL = "INSERT INTO " + STUDENT_TABLE_NAME + "(" + STUDENT_FIRST_COLUMN + ", " + STUDENT_LAST_COLUMN +", " + STUDENT_PHONE_COLUMN + ")" + " VALUES ('Sarah', 'Kwabi', '555-555-1414')";
            ConnectDB.statement.executeUpdate(addDataSQL);
            addDataSQL = "INSERT INTO " + STUDENT_TABLE_NAME + "(" + STUDENT_FIRST_COLUMN + ", " + STUDENT_LAST_COLUMN +", " + STUDENT_PHONE_COLUMN + ")" + " VALUES ('Caleb', 'Mohammad', '555-555-1515')";
            ConnectDB.statement.executeUpdate(addDataSQL);
        }
        catch (SQLException se){
            System.out.println(se);
            se.printStackTrace();
        }
    }

    public void AddToTeacher(){

        try {


            String addDataSQL2 = "INSERT INTO " + TEACHER_TABLE_NAME + "(" + TEACHER_FIRST_COLUMN + ", " + TEACHER_LAST_COLUMN +", " + TEACHER_PHONE_COLUMN + ")" + " VALUES ('Johann', 'Brahams', '654-654-6541')";
            ConnectDB.statement.executeUpdate(addDataSQL2);
            addDataSQL2 = "INSERT INTO " + TEACHER_TABLE_NAME + "(" + TEACHER_FIRST_COLUMN + ", " + TEACHER_LAST_COLUMN +", " + TEACHER_PHONE_COLUMN + ")" + " VALUES ('Wolfgang', 'Mozart', '321-321-3214')";
            ConnectDB.statement.executeUpdate(addDataSQL2);
            addDataSQL2 = "INSERT INTO " + TEACHER_TABLE_NAME + "(" + TEACHER_FIRST_COLUMN + ", " + TEACHER_LAST_COLUMN +", " + TEACHER_PHONE_COLUMN + ")" + " VALUES ('Samuel', 'Barber', '987-987-9874')";
            ConnectDB.statement.executeUpdate(addDataSQL2);
        }
        catch (SQLException sqle){
            System.out.println(sqle);
        }
    }

    public void AddToClass(){

        try {

            String createTableSQL2 = "INSERT INTO " + CLASS_TABLE_NAME + " (" + CLASS_NAME_COLUMN + ", " + CLASS_DAY_COLUMN + ", " + CLASS_TIME_COLUMN + ", " + CLASS_PRICE_COLUMN +", " + TEACHER_PK_FK + ") VALUES ('Beginning Cello', 'Tuesday', '8:00am', '25.00', 1)";
            ConnectDB.statement.executeUpdate(createTableSQL2);
            createTableSQL2 = "INSERT INTO " + CLASS_TABLE_NAME + " (" + CLASS_NAME_COLUMN + ", " + CLASS_DAY_COLUMN + ", " + CLASS_TIME_COLUMN + ", " + CLASS_PRICE_COLUMN +", " + TEACHER_PK_FK + ") VALUES ('Beginning Violin', 'Tuesday', '9:00am', '25.00', 2)";
            ConnectDB.statement.executeUpdate(createTableSQL2);
            createTableSQL2 = "INSERT INTO " + CLASS_TABLE_NAME + " (" + CLASS_NAME_COLUMN + ", " + CLASS_DAY_COLUMN + ", " + CLASS_TIME_COLUMN + ", " + CLASS_PRICE_COLUMN +", " + TEACHER_PK_FK + ") VALUES ('Beginning Cello', 'Wednesday', '4:00pm', '15.00', 2)";
            ConnectDB.statement.executeUpdate(createTableSQL2);
            createTableSQL2 = "INSERT INTO " + CLASS_TABLE_NAME + " (" + CLASS_NAME_COLUMN + ", " + CLASS_DAY_COLUMN + ", " + CLASS_TIME_COLUMN + ", " + CLASS_PRICE_COLUMN +", " + TEACHER_PK_FK + ") VALUES ('Advanced Cello', 'Monday', '8:00am', '35.00', 3)";
            ConnectDB.statement.executeUpdate(createTableSQL2);
        }
        catch (SQLException sqle){
            System.out.println(sqle);
        }
    }

    public void AddToStudentClass () {
        try {
            String addToStudClassTableSQL = "INSERT INTO " + STUDENT_CLASS_TABLE_NAME + " (" + STUD_FK_PK_COL + ", " + CLASS_FK_PK_COL + ") VALUES ( ?, ? )";
            PreparedStatement insertToStCl = ConnectDB.conn.prepareStatement(addToStudClassTableSQL);
            insertToStCl.setInt(1, 1);
            insertToStCl.setInt(2, 2);
            insertToStCl.executeUpdate();
            insertToStCl.setInt(1, 1);
            insertToStCl.setInt(2, 3);
            insertToStCl.executeUpdate();
            insertToStCl.setInt(1, 3);
            insertToStCl.setInt(2, 4);
            insertToStCl.executeUpdate();
        }
        catch (SQLException sqle){
            System.out.println("In add to student " + sqle);
        }
    }
    // These methods are from Clara. The have only been updated to reflect tables in musicschool database.
    private boolean studentTableExists() throws SQLException{

        String checkTablePresentQuery = "SHOW TABLES LIKE '" + STUDENT_TABLE_NAME + "'";   //Can query the database schema
        ResultSet tablesRS = ConnectDB.statement.executeQuery(checkTablePresentQuery);
        return (tablesRS.next());
    }

    private boolean teacherTableExists() throws SQLException {

        String checkTablePresentQuery = "SHOW TABLES LIKE '" + TEACHER_TABLE_NAME + "'";   //Can query the database schema
        ResultSet tablesRS = ConnectDB.statement.executeQuery(checkTablePresentQuery);
        return tablesRS.next();
    }

    private boolean classTableExists() throws SQLException {

        String checkTablePresentQuery = "SHOW TABLES LIKE '" + CLASS_TABLE_NAME + "'";   //Can query the database schema
        ResultSet tablesRS = ConnectDB.statement.executeQuery(checkTablePresentQuery);
        return tablesRS.next();
    }

    private boolean studentClassTableExists() throws SQLException {

        String checkTablePresentQuery = "SHOW TABLES LIKE '" + STUDENT_CLASS_TABLE_NAME + "'";   //Can query the database schema
        ResultSet tablesRS = ConnectDB.statement.executeQuery(checkTablePresentQuery);
        return tablesRS.next();
    }

}
