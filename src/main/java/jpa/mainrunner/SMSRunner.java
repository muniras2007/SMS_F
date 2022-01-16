package jpa.mainrunner;


import jpa.entitymodels.Course;
import jpa.entitymodels.Student;
import jpa.service.CourseService;
import jpa.service.StudentService;

import java.util.List;
import java.util.Scanner;

public class SMSRunner {
//declare everything
    private Scanner sin;
    private StringBuilder sb;

    private CourseService courseService;
    private StudentService studentService;
    private Student currentStudent;
//initialise
    public SMSRunner() {
        sin = new Scanner(System.in);
        sb = new StringBuilder();
        courseService = new CourseService();
        studentService = new StudentService();
    }

    /**
     * @munira
     */
    //main code runs from here
    public static void main(String[] args) {

       SMSRunner sms = new SMSRunner();
       sms.run();
//I used these to test my methods from the service class only not to run the code

//        StudentService ss = new StudentService();
//        List<Student> studentList = ss.getAllStudents();
//       for(Student student : studentList){
//           System.out.println(student);
//        }
//
//        System.out.println(ss.getStudentByEmail("htaffley6@columbia.edu"));
//       System.out.println(ss.validateStudent("htaffley6@columbia.edu", "xowtOQ"));
//        System.out.println(ss.getStudentCourses("htaffley6@columbia.edu"));
//        ss.registerStudentToCourse("htaffley6@columbia.edu", 1);



    }
 // displays nd asks the user t select what they wanna do
    private void run() {
        // Login or quit
        switch (menu1()) {
            case 1:
                if (studentLogin()) {
                    registerMenu();
                }
                break;
            case 2:
                System.out.println("Goodbye!");
                break;

            default:

        }
    }
    //user selects to log in or stop
    private int menu1() {
        sb.append("\n1.Student Login\n2. Quit Application\nPlease Enter Selection: ");
        System.out.print(sb.toString());
        sb.delete(0, sb.length());

        return sin.nextInt();
    }
    // prompted to enter their email and password and they either log in or wrong info and they are out
    private boolean studentLogin() {

            boolean retValue = false;
            System.out.print("Enter your email address: ");
            String email = sin.next();
            System.out.print("Enter your password: ");
            String password = sin.next();
//if email and password are true they log in otherwise validation fails.If in they see their courses
            currentStudent = studentService.getStudentByEmail(email);

            if (studentService.validateStudent(email, password)) {
                List<Course> courses = studentService.getStudentCourses(email);
                System.out.println("MyClasses");
                for (Course course : courses) {
                    System.out.println(course);
                }
                retValue = true;
            } else {
                System.out.println("User Validation failed. GoodBye!");
            }
            return retValue;
        }

    //option to register for class or log out
    private void registerMenu() {
        sb.append("\n1.Register a class\n2. Logout\nPlease Enter Selection: ");
        System.out.print(sb.toString());
        sb.delete(0, sb.length());

        switch (sin.nextInt()) {
            case 1:
                List<Course> allCourses = courseService.getAllCourses();
                List<Course> studentCourses = studentService.getStudentCourses(currentStudent.getsEmail());
                allCourses.removeAll(studentCourses);
                System.out.printf("%5s%15S%15s\n", "ID", "Course", "Instructor");
                for (Course course : allCourses) {
                    System.out.println(course);
                }
                System.out.println();
                System.out.print("Enter Course Number: ");
                int number = sin.nextInt();
                //Course newCourse = courseService.GetCourseById(number);


                //if (newCourse != null) {
                studentService.registerStudentToCourse(currentStudent.getsEmail(), number);
                Student temp = studentService.getStudentByEmail(currentStudent.getsEmail());
                //after registration they see their updated courses and it ends
                //StudentCourseService scService = new StudentCourseService();
                List<Course> sCourses = studentService.getStudentCourses(temp.getsEmail());


                System.out.println("MyClasses");

                System.out.printf("%-5s%-35S%-15s\n", "ID", "Course", "Instructor");

                for (Course course : sCourses) {
                    System.out.println(sCourses);
                    System.out.printf("%-5s%-35S%-15s\n", course.getcId(), course.getcName(), course.getcInstructorName());
                }
                // }
                break;
            case 2:
            default:
                System.out.println("Goodbye!");
        }
    }
}

