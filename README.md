# ElkinsMusicSchool

Hello Clara:

Welcome to the admin portal for the Elkins School of Music. You have the option to:
 -- add a new student
 -- add a new teacher
 -- add a new class
 -- enroll a student in a class
 -- view a student's schedule
 -- view a teacher's schedule

There are no known bugs that you need to be aware of, but not all user input is validated so there may be a few weak spots. Also, since the program creates the database, but doesn't have a way to check if it exists, so if you run it multiple times without dropping the musicschool schema/database in mySQL, the program will attempt to create the DB and give the standard console error message that the database already exists.

To run this program:
1) download the code from this Github repository
2) get your mySQL server running
3) go into the ConnectDB.java class in the program and change the following code as noted in the comments:
public class ConnectDB {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  // fixed
    static final String DB_CONNECTION_URL = "jdbc:mysql://localhost:3306/";  // fixed
    static final String DB_NAME = "musicschool";  // fixed
    // database created using command line for my sql
    static final String USER = "root";  // TODO Clara - replace root with your user name here
    static final String PASS = "cello"; // TODO Clara - replace cello with your password here
    static Connection conn = null;
    static Statement statement = null;
    public static Statement tableStatement = null;
4) run Main

I don't think you'll have to look hard to find all the available features. The program was created to be user friendly,
with all necessary instruction and feedback on screen.

Thanks and enjoy!

Margaret
