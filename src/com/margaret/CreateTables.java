package com.margaret;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by sn0173nd on 12/2/2015.
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

    // Student class table name, pks and columns
    public final static String STUDENT_CLASS_TABLE_NAME = "StudentClass";
    public final static String STUDENT_CLASS_ID = "StudClassID";
    public final static String STUD_FK_PK_COL = "StudentID";
    public final static String CLASS_FK_PK_COL = "ClassID";

    public CreateTables(){
        StartTables();
    }

    public void StartTables() {
        System.out.println("in start tables");

        try {

            if (!studentTableExists()) {

                //Create a student table in the database with TODO columns and name fields
                String createTableSQL = "CREATE TABLE " + STUDENT_TABLE_NAME + " (" + STUDENT_PK_COL + " int NOT NULL AUTO_INCREMENT PRIMARY KEY, " + STUDENT_FIRST_COLUMN + " varchar(50), " + STUDENT_LAST_COLUMN + " varchar(50), " + STUDENT_PHONE_COLUMN + " varchar(12)" + ")";
                ConnectDB.statement.executeUpdate(createTableSQL);

                System.out.println("Created student table");

                AddToStudent();
            }

            if (!teacherTableExists()) {

                //Create a table in the database with TODO columns and name fields
                String createTableSQL2 = "CREATE TABLE " + TEACHER_TABLE_NAME + " (" + TEACHER_PK_COL + " int NOT NULL AUTO_INCREMENT PRIMARY KEY, " + TEACHER_FIRST_COLUMN + " varchar(50), " + TEACHER_LAST_COLUMN + " varchar(50), " + TEACHER_PHONE_COLUMN + " varchar(50))";
                ConnectDB.statement.executeUpdate(createTableSQL2);

                System.out.println("Created teacher table");

                AddToTeacher();
            }

            if (!classTableExists()) {

                //Create a table in the database with TODO columns and name fields
                String createTableSQL2 = "CREATE TABLE " + CLASS_TABLE_NAME + " (" + CLASS_PK_COL + " int NOT NULL AUTO_INCREMENT PRIMARY KEY, " + CLASS_NAME_COLUMN + " varchar(50), " + CLASS_DAY_COLUMN + " varchar(50), " + CLASS_TIME_COLUMN + " varchar (50), " + CLASS_PRICE_COLUMN + " DECIMAL (5,2), " + TEACHER_PK_FK + " INT)";
                System.out.println(createTableSQL2);
                ConnectDB.statement.executeUpdate(createTableSQL2);

                System.out.println("Created class table");

                AddToClass();
                MakeTeacherFKinClass();
            }

            if (!studentClassTableExists()){
                System.out.println("Student Class Table does not exist");
                String createTableSQL2 = "CREATE TABLE " + STUDENT_CLASS_TABLE_NAME + " (" + STUD_FK_PK_COL + " int NOT NULL, " + CLASS_FK_PK_COL + " int NOT NULL, PRIMARY KEY (" + STUD_FK_PK_COL + ", " + CLASS_FK_PK_COL + "))";
                System.out.println(createTableSQL2);
                ConnectDB.statement.executeUpdate(createTableSQL2);

                System.out.println("Created  student class table");
            }
        }
        catch (SQLException sqle){
            System.out.println(sqle);
        }
    }


    public void MakeTeacherFKinClass () {
        try {

//            String addFKData = "UPDATE " + HARVESTS_TABLE_NAME + " SET " + STUDENT_PK_COL + " = 2 WHERE " + HARVESTS_PK_COL + " = 1;";
//            System.out.println(addFKData);
//            statement.executeUpdate(addFKData);
//            addFKData = "UPDATE " + HARVESTS_TABLE_NAME + " SET " + STUDENT_PK_COL + " = 2 WHERE " + HARVESTS_PK_COL + " = 2;";
//            statement.executeUpdate(addFKData);
//            addFKData = "UPDATE " + HARVESTS_TABLE_NAME + " SET " + STUDENT_PK_COL + " = 1 WHERE " + HARVESTS_PK_COL + " = 3;";
//            statement.executeUpdate(addFKData);



            System.out.println("Added foreign key data to harvests");

            String constraintName = "hiveFKConst";
            String fkConstraint = "ALTER TABLE " + CLASS_TABLE_NAME + " MODIFY COLUMN " + TEACHER_PK_COL + " INT NOT NULL, ADD CONSTRAINT " + constraintName + " FOREIGN KEY(" + TEACHER_PK_FK + ") REFERENCES " + TEACHER_TABLE_NAME + "(" + TEACHER_PK_COL + ");";
            System.out.println(fkConstraint);
            ConnectDB.statement.executeUpdate(fkConstraint);

    //                ALTER TABLE harvests MODIFY COLUMN hiveID INT NOT NULL,
    //                        ADD CONSTRAINT hiveFKConst
    //                FOREIGN KEY(hiveID)
    //                        REFERENCES hives(hiveID);

            System.out.println("Foreign key constraint added");

        } catch(SQLException se){
            System.out.println(se);  // TODO add more info to exception message
            se.printStackTrace();
        }
    }

    public void AddToStudent(){

        // Add some test data
        // Here we have to specify which columns the data will go into, because we want to omit the ID column and have MySQL fill it in for us.

        // TODO do I want to add instantiations?

        try {

            String addDataSQL = "INSERT INTO " + STUDENT_TABLE_NAME + "(" + STUDENT_FIRST_COLUMN + ", " + STUDENT_LAST_COLUMN + ", " + STUDENT_PHONE_COLUMN + ")" + " VALUES ('Margaret', 'Elkins', '555-555-1212')";
            System.out.println(addDataSQL);
            ConnectDB.statement.executeUpdate(addDataSQL);
            addDataSQL = "INSERT INTO " + STUDENT_TABLE_NAME + "(" + STUDENT_FIRST_COLUMN + ", " + STUDENT_LAST_COLUMN +", " + STUDENT_PHONE_COLUMN + ")" + " VALUES ('Scott', 'Sivad', '555-555-1313')";
            System.out.println(addDataSQL);
            ConnectDB.statement.executeUpdate(addDataSQL);
            addDataSQL = "INSERT INTO " + STUDENT_TABLE_NAME + "(" + STUDENT_FIRST_COLUMN + ", " + STUDENT_LAST_COLUMN +", " + STUDENT_PHONE_COLUMN + ")" + " VALUES ('Sarah', 'Kwabi', '555-555-1414')";
            ConnectDB.statement.executeUpdate(addDataSQL);
            addDataSQL = "INSERT INTO " + STUDENT_TABLE_NAME + "(" + STUDENT_FIRST_COLUMN + ", " + STUDENT_LAST_COLUMN +", " + STUDENT_PHONE_COLUMN + ")" + " VALUES ('Caleb', 'Mohammad', '555-555-1515')";
            System.out.println(addDataSQL);
            ConnectDB.statement.executeUpdate(addDataSQL);
        }
        catch (SQLException se){
            System.out.println(se);
            se.printStackTrace();
        }
    }

    public void AddToTeacher(){

        // Add some test data
        // Here we have to specify which columns the data will go into, because we want to omit the ID column and have MySQL fill it in for us.

        try {

            String addDataSQL2 = "INSERT INTO " + TEACHER_TABLE_NAME + "(" + TEACHER_FIRST_COLUMN + ", " + TEACHER_LAST_COLUMN +", " + TEACHER_PHONE_COLUMN + ")" + " VALUES ('Johann', 'Brahams', '654-654-6541')";
            System.out.println(addDataSQL2);
            ConnectDB.statement.executeUpdate(addDataSQL2);
        addDataSQL2 = "INSERT INTO " + TEACHER_TABLE_NAME + "(" + TEACHER_FIRST_COLUMN + ", " + TEACHER_LAST_COLUMN +", " + TEACHER_PHONE_COLUMN + ")" + " VALUES ('Wolfgang', 'Mozart', '321-321-3214')";System.out.println(addDataSQL2);
            ConnectDB.statement.executeUpdate(addDataSQL2);
            addDataSQL2 = "INSERT INTO " + TEACHER_TABLE_NAME + "(" + TEACHER_FIRST_COLUMN + ", " + TEACHER_LAST_COLUMN +", " + TEACHER_PHONE_COLUMN + ")" + " VALUES ('Samuel', 'Barber', '987-987-9874')";
            System.out.println(addDataSQL2);
            ConnectDB.statement.executeUpdate(addDataSQL2);
        }
        catch (SQLException sqle){
            System.out.println(sqle);
        }
    }

    public void AddToClass(){

        // Add some test data
        // Here we have to specify which columns the data will go into, because we want to omit the ID column and have MySQL fill it in for us.

        try {

            String createTableSQL2 = "INSERT INTO " + CLASS_TABLE_NAME + " (" + CLASS_NAME_COLUMN + ", " + CLASS_DAY_COLUMN + ", " + CLASS_TIME_COLUMN + ", " + CLASS_PRICE_COLUMN +", " + TEACHER_PK_FK + ") VALUES ('Beginning Cello', 'Tuesday', '8:00am', '25.00', 1)";
            System.out.println(createTableSQL2);
            ConnectDB.statement.executeUpdate(createTableSQL2);
            createTableSQL2 = "INSERT INTO " + CLASS_TABLE_NAME + " (" + CLASS_NAME_COLUMN + ", " + CLASS_DAY_COLUMN + ", " + CLASS_TIME_COLUMN + ", " + CLASS_PRICE_COLUMN +", " + TEACHER_PK_FK + ") VALUES ('Beginning Violin', 'Tuesday', '9:00am', '25.00', 2)";
            System.out.println(createTableSQL2);
            ConnectDB.statement.executeUpdate(createTableSQL2);
            createTableSQL2 = "INSERT INTO " + CLASS_TABLE_NAME + " (" + CLASS_NAME_COLUMN + ", " + CLASS_DAY_COLUMN + ", " + CLASS_TIME_COLUMN + ", " + CLASS_PRICE_COLUMN +", " + TEACHER_PK_FK + ") VALUES ('Beginning Cello', 'Wednesday', '4:00pm', '15.00', 2)";
            System.out.println(createTableSQL2);
            ConnectDB.statement.executeUpdate(createTableSQL2);
            createTableSQL2 = "INSERT INTO " + CLASS_TABLE_NAME + " (" + CLASS_NAME_COLUMN + ", " + CLASS_DAY_COLUMN + ", " + CLASS_TIME_COLUMN + ", " + CLASS_PRICE_COLUMN +", " + TEACHER_PK_FK + ") VALUES ('Advanced Cello', 'Monday', '8:00am', '35.00', 3)";
            System.out.println(createTableSQL2);
            ConnectDB.statement.executeUpdate(createTableSQL2);
        }
        catch (SQLException sqle){
            System.out.println(sqle);
        }
    }

    private boolean studentTableExists() throws SQLException{

        String checkTablePresentQuery = "SHOW TABLES LIKE '" + STUDENT_TABLE_NAME + "'";   //Can query the database schema
        System.out.println(checkTablePresentQuery);
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
