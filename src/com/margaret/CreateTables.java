package com.margaret;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by sn0173nd on 12/2/2015.
 */
public class CreateTables {

    // Student table name, pk and columns
    public final static String STUDENT_TABLE_NAME = "Student";
    public final static String STUDENT_PK_COL = "StudentID";
    public final static String STUDENT_FIRST_COLUMN = "FirstName";
    public final static String STUDENT_LAST_COLUMN = "LastName";
    public final static String STUDENT_PHONE_COLUMN = "Phone";

    // Teacher table name, pk and columns
    public final static String HARVESTS_TABLE_NAME = "";
    public final static String HARVESTS_PK_COL = "";
    public final static String B_COLUMN = "";
    public final static String C_COLUMN = "";

    // Class table name, pk and columns
    public final static String CLASS_TABLE_NAME = "";
    public final static String CLASS_PK_COL = "";
    public final static String D_COLUMN = "";
    public final static String E_COLUMN = "";

    // Student class table name, pks and columns
    public final static String STUDENT_CLASS_TABLE_NAME = "";
    public final static String STUD_FK_PK_COL = "";
    public final static String CLASS_FK_PK_COL = "";
    public final static String DD_PK_COLUMN = "";

    public CreateTables(){
        StartTables();
        AddToStudent();
    }

    public void StartTables() {
        System.out.println("in start tables");

        try {
            if (!studentTableExists()) {

                Statement statement = null;

                //Create a student table in the database with TODO columns and name fields
                String createTableSQL = "CREATE TABLE " + STUDENT_TABLE_NAME + " (" + STUDENT_PK_COL + " int NOT NULL AUTO_INCREMENT PRIMARY KEY, " + STUDENT_FIRST_COLUMN + " varchar(50), " + STUDENT_LAST_COLUMN + " varchar(50), " + STUDENT_PHONE_COLUMN+ " varchar(12)" + ")";
                System.out.println(createTableSQL);
                statement.executeUpdate(createTableSQL);

                System.out.println("Created student table");

                AddToStudent();
            }


//            if (!teacherTableExists()) {
//
//                //Create a table in the database with 3 columns: Movie title, year and rating
//                String createTableSQL2 = "CREATE TABLE " + HARVESTS_TABLE_NAME + " (" + HARVESTS_PK_COL + " int NOT NULL AUTO_INCREMENT PRIMARY KEY, " + DATE_COLUMN + " varchar(50), \n" + WEIGHT_COLUMN + " int)";
//                System.out.println(createTableSQL2);
//                statement.executeUpdate(createTableSQL2);
//
//                System.out.println("Created harvests table");
//
//                String addColumnSQL = "ALTER TABLE " + HARVESTS_TABLE_NAME + " ADD COLUMN " + STUDENT_PK_COL + " INT;";
//                System.out.println(addColumnSQL);
//                statement.executeUpdate(addColumnSQL);
//
//                System.out.println("Added hiveID column to harvests");
//
//
//                // Add some test data - change to some movies you like, if desired
//                //Example SQL: INSERT INTO movie_reviews ( title, year_released, rating ) VALUES ( 'Back to the future', 1985, 5)
//                //Here we have to specify which columns the data will go into, because we want to omit the ID column and have MySQL fill it in for us.
//                //But, since we are only adding 3 pieces of data for 4 columns, we have to specify which columns each data item is for.
//
//
//                String addDataSQL2 = "INSERT INTO " + HARVESTS_TABLE_NAME + "(" + DATE_COLUMN + ", " + WEIGHT_COLUMN + ")" + " VALUES ('date1', 100)";
//                System.out.println(addDataSQL2);
//                statement.executeUpdate(addDataSQL2);
//                addDataSQL2 = "INSERT INTO " + HARVESTS_TABLE_NAME + "(" + DATE_COLUMN + ", " + WEIGHT_COLUMN + ")" + " VALUES ('date2', 200)";
//                System.out.println(addDataSQL2);
//                statement.executeUpdate(addDataSQL2);
//                addDataSQL2 = "INSERT INTO " + HARVESTS_TABLE_NAME + "(" + DATE_COLUMN + ", " + WEIGHT_COLUMN + ")" + " VALUES ('date3', 300)";
//                System.out.println(addDataSQL2);
//                statement.executeUpdate(addDataSQL2);
//            }
//
//            if (!classTableExists()) {
//
//                //Create a table in the database with 3 columns: Movie title, year and rating
//                String createTableSQL2 = "CREATE TABLE " + HARVESTS_TABLE_NAME + " (" + HARVESTS_PK_COL + " int NOT NULL AUTO_INCREMENT PRIMARY KEY, " + DATE_COLUMN + " varchar(50), \n" + WEIGHT_COLUMN + " int)";
//                System.out.println(createTableSQL2);
//                statement.executeUpdate(createTableSQL2);
//
//                System.out.println("Created harvests table");
//
//                String addColumnSQL = "ALTER TABLE " + HARVESTS_TABLE_NAME + " ADD COLUMN " + STUDENT_PK_COL + " INT;";
//                System.out.println(addColumnSQL);
//                statement.executeUpdate(addColumnSQL);
//
//                System.out.println("Added hiveID column to harvests");
//
//
//                // Add some test data - change to some movies you like, if desired
//                //Example SQL: INSERT INTO movie_reviews ( title, year_released, rating ) VALUES ( 'Back to the future', 1985, 5)
//                //Here we have to specify which columns the data will go into, because we want to omit the ID column and have MySQL fill it in for us.
//                //But, since we are only adding 3 pieces of data for 4 columns, we have to specify which columns each data item is for.
//
//
//                String addDataSQL2 = "INSERT INTO " + HARVESTS_TABLE_NAME + "(" + DATE_COLUMN + ", " + WEIGHT_COLUMN + ")" + " VALUES ('date1', 100)";
//                System.out.println(addDataSQL2);
//                statement.executeUpdate(addDataSQL2);
//                addDataSQL2 = "INSERT INTO " + HARVESTS_TABLE_NAME + "(" + DATE_COLUMN + ", " + WEIGHT_COLUMN + ")" + " VALUES ('date2', 200)";
//                System.out.println(addDataSQL2);
//                statement.executeUpdate(addDataSQL2);
//                addDataSQL2 = "INSERT INTO " + HARVESTS_TABLE_NAME + "(" + DATE_COLUMN + ", " + WEIGHT_COLUMN + ")" + " VALUES ('date3', 300)";
//                System.out.println(addDataSQL2);
//                statement.executeUpdate(addDataSQL2);
//            }
//
//            if (!studentClassTableExists()){
//                //Create a table in the database with 3 columns: Movie title, year and rating
//                String createTableSQL = "CREATE TABLE " + STUDENT_CLASS_TABLE_NAME + " (" + STUD_FK_PK_COL + " int NOT NULL AUTO_INCREMENT PRIMARY KEY, " + DATE_COLUMN + " varchar(50), \n" + WEIGHT_COLUMN + " int)";
//                System.out.println(createTableSQL);
//                statement.executeUpdate(createTableSQL);
//
//                System.out.println("Created harvests table");
//            }
        } catch(SQLException se){
            System.out.println(se);
            se.printStackTrace();
        }
    }


    public void MakeTeacherFKinClass () {
        try {

            Statement statement = null;

            String addColumnSQL = "ALTER TABLE " + HARVESTS_TABLE_NAME + " ADD COLUMN " + STUDENT_PK_COL + " INT;";
            System.out.println(addColumnSQL);
            statement.executeUpdate(addColumnSQL);

            System.out.println("Added hiveID column to harvests");

            String addFKData = "UPDATE " + HARVESTS_TABLE_NAME + " SET " + STUDENT_PK_COL + " = 2 WHERE " + HARVESTS_PK_COL + " = 1;";
            System.out.println(addFKData);
            statement.executeUpdate(addFKData);
            addFKData = "UPDATE " + HARVESTS_TABLE_NAME + " SET " + STUDENT_PK_COL + " = 2 WHERE " + HARVESTS_PK_COL + " = 2;";
            statement.executeUpdate(addFKData);
            addFKData = "UPDATE " + HARVESTS_TABLE_NAME + " SET " + STUDENT_PK_COL + " = 1 WHERE " + HARVESTS_PK_COL + " = 3;";
            statement.executeUpdate(addFKData);

            System.out.println("Added foreign key data to harvests");

            String constraintName = "hiveFKConst";
            String fkConstraint = "ALTER TABLE " + HARVESTS_TABLE_NAME + " MODIFY COLUMN " + STUDENT_PK_COL + " INT NOT NULL, ADD CONSTRAINT " + constraintName + " FOREIGN KEY(" + STUDENT_PK_COL + ") REFERENCES " + STUDENT_TABLE_NAME + "(" + STUDENT_PK_COL + ");";
            System.out.println(fkConstraint);
            statement.executeUpdate(fkConstraint);

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

        try {
            Statement statement = null;

            String addDataSQL = "INSERT INTO " + STUDENT_TABLE_NAME + "(" + STUDENT_FIRST_COLUMN + ", " + STUDENT_LAST_COLUMN +", " + STUDENT_PHONE_COLUMN + ")" + " VALUES ('Margaret', 'Elkins', '555-555-1212')";
            System.out.println(addDataSQL);
            statement.executeUpdate(addDataSQL);
            addDataSQL = "INSERT INTO " + STUDENT_TABLE_NAME + "(" + STUDENT_FIRST_COLUMN + ", " + STUDENT_LAST_COLUMN +", " + STUDENT_PHONE_COLUMN + ")" + " VALUES ('Scott', 'Sivad', '555-555-1313')";
            System.out.println(addDataSQL);
            statement.executeUpdate(addDataSQL);
            addDataSQL = "INSERT INTO " + STUDENT_TABLE_NAME + "(" + STUDENT_FIRST_COLUMN + ", " + STUDENT_LAST_COLUMN +", " + STUDENT_PHONE_COLUMN + ")" + " VALUES ('Sarah', 'Kwabi', '555-555-1414')";
            statement.executeUpdate(addDataSQL);
            addDataSQL = "INSERT INTO " + STUDENT_TABLE_NAME + "(" + STUDENT_FIRST_COLUMN + ", " + STUDENT_LAST_COLUMN +", " + STUDENT_PHONE_COLUMN + ")" + " VALUES ('Caleb', 'Mohammad', '555-555-1515')";
            System.out.println(addDataSQL);
            statement.executeUpdate(addDataSQL);
        }
        catch (SQLException se){
            System.out.println(se);
            se.printStackTrace();
        }
    }

    private boolean studentTableExists() {

        Statement statement = null;

        try {

            String checkTablePresentQuery = "SHOW TABLES LIKE '" + STUDENT_TABLE_NAME + "'";   //Can query the database schema
            System.out.println(checkTablePresentQuery);
            ResultSet tablesRS = statement.executeQuery(checkTablePresentQuery);
            return (tablesRS.next());
        }
        catch (SQLException sqle){
            System.out.println("in student table exists " + sqle);
        }
        catch (Exception e){
            System.out.println("in student table exists not sql error " + e);
        }
        return false;
    }

    private boolean teacherTableExists() throws SQLException {

        Statement statement = null;

        String checkTablePresentQuery = "SHOW TABLES LIKE '" + HARVESTS_TABLE_NAME + "'";   //Can query the database schema
        ResultSet tablesRS = statement.executeQuery(checkTablePresentQuery);
        return tablesRS.next();

    }

    private boolean classTableExists() throws SQLException {

        Statement statement = null;

        String checkTablePresentQuery = "SHOW TABLES LIKE '" + CLASS_TABLE_NAME + "'";   //Can query the database schema
        ResultSet tablesRS = statement.executeQuery(checkTablePresentQuery);
        return tablesRS.next();
    }

    private boolean studentClassTableExists() throws SQLException {

        Statement statement = null;

        String checkTablePresentQuery = "SHOW TABLES LIKE '" + STUDENT_CLASS_TABLE_NAME + "'";   //Can query the database schema
        ResultSet tablesRS = statement.executeQuery(checkTablePresentQuery);
        return tablesRS.next();

    }

}
