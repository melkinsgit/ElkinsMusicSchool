package com.margaret;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by sn0173nd on 12/2/2015.
 */
public class MenuOne {

    public MenuOne () {
        while (true) {
            FirstFourChoices();
        }
    }

    Scanner s = new Scanner(System.in);

    public void FirstFourChoices(){
        System.out.println("Choose one:\n\t1. Add Student\n\t2. Add Teacher\n\t3. Enroll in a Class\n\t4. Finance");
        String choice1Str = s.nextLine();
        int choice1 = Integer.parseInt(choice1Str);

        switch (choice1){

            case 1:{
                // TODO make GUI
                // TODO validate user input
                System.out.println("STUDENT\nEnter First Name:");
                String StFirst = s.nextLine();
                System.out.println("Enter Last name:");
                String StLast = s.nextLine();
                System.out.println("Enter Phone:");
                String StPhone = s.nextLine();
                Student student = new Student (StFirst, StLast, StPhone);  // TODO do I want to add instantiations?
                student.AddStudent(StFirst, StLast, StPhone);
                ResultSet studentsRS = student.AllDataQuery();
                student.DisplayAllStudents(studentsRS);
                break;
            }
            case 2:{
                // TODO make GUI
                // TODO validate user input
                System.out.println("TEACHER\nEnter First Name:");
                String TFirst = s.nextLine();
                System.out.println("Enter Last name:");
                String TLast = s.nextLine();
                System.out.println("Enter Phone:");
                String TPhone = s.nextLine();
                Teacher teacher = new Teacher (TFirst, TLast, TPhone);  // TODO do I want to add instantiations?
                teacher.AddTeacher(TFirst, TLast, TPhone);
                ResultSet teacherRS = teacher.AllDataQuery();
                teacher.DisplayAllTeachers(teacherRS);
                break;
            }
            case 3:{
                MusicClass musicClass = new MusicClass();
                ResultSet classesRS = musicClass.AllDataQuery();
                int classToTake = musicClass.DisplayAllClasses(classesRS);
                musicClass.EnrollInClass(classToTake);
                break;
            }
            case 4:{
                break;
            }
        }
    }
}
