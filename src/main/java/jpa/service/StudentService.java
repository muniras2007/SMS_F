package jpa.service;

import jpa.dao.StudentDAO;
import jpa.entitymodels.Course;
import jpa.entitymodels.Student;

import javax.persistence.*;
import java.util.List;



public class StudentService implements StudentDAO {

    //This method reads the student table in your database and returns
    //the data as a List<Student>

    private EntityManagerFactory emfactory;
    private EntityManager entityManager;
    public StudentService() {

         emfactory = Persistence.createEntityManagerFactory("SMS");
        entityManager = emfactory.createEntityManager();

    }

    @Override
    public List<Student> getAllStudents() {
        //Open DB connection,query the db to get list of students, close the connection

//        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("SMS");
//        EntityManager entityManager =emfactory.createEntityManager();

        TypedQuery<Student> query=entityManager.createQuery("SELECT s FROM Student s",Student.class);

            List<Student>studentList = query.getResultList();

//        entityManager.close();
//        emfactory.close();

        return studentList;

        }
    //This method takes a Student’s email as a String and parses the student list
    //for a Student with that email and returns a Student Object.

    @Override
    public Student getStudentByEmail(String sEmail) {
//        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("SMS");
//        EntityManager entityManager =emfactory.createEntityManager();

        Student foundStudent = entityManager.find(Student.class, sEmail);
        if (foundStudent == null) {
            throw new EntityNotFoundException("Can't find Student Registration for :" + sEmail);
        }
        // close the entity manager
//        entityManager.close();
//        emfactory.close();
        return foundStudent;

    }
    ////This method takes two parameters: the first one is the user email and the second one is the password from the user input.
    //	//Return whether or not student was found.

    @Override
    public boolean validateStudent(String sEmail, String sPassword) {
//        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("SMS");
//        EntityManager entityManager =emfactory.createEntityManager();
        Student foundStudent = entityManager.find(Student.class, sEmail);
        if (foundStudent == null) {
            //	throw new EntityNotFoundException("Can't find Student Registration for :" + sEmail);
            System.out.println("Cannot find Student Registration for : " + sEmail);
            // close the entity manager
//            entityManager.close();
//            emfactory.close();
            return false;

        }
        else if (!foundStudent.getsPass().equals(sPassword)) {
            System.out.println("Incorrect Password.");
            // close the entity manager
//            entityManager.close();
//            emfactory.close();
            return false;
        }else {
            // close the entity manager
//            entityManager.close();
//            emfactory.close();
            return true;
        }


    }
    ////After a successful student validation, this method takes a Student’s email
    //	and a Course ID. It checks in the join table (i.e. Student_Course) generated by
    //	JPA to find if a Student with that Email is currently attending a Course with that ID.
    //	If the Student is not attending that Course, register the student
    //	to that course; otherwise not.


    @Override
    public void registerStudentToCourse(String sEmail, int cId) {

//        try {
//            EntityManagerFactory entitymanagerfactory = Persistence.createEntityManagerFactory("SMS");
//            EntityManager entitymanager = entitymanagerfactory.createEntityManager();
            entityManager.getTransaction().begin();
            Course cou = entityManager.find(Course.class, cId);
            //cou =entitymanager.find(Course.class, cId);

//            Student student= new Student();
//            StudentService stu = new StudentService();
            Student student =getStudentByEmail(sEmail);
            System.out.println();
            System.out.println("Course="+cou.getcName());
            System.out.println("Student="+student.getsName());
            System.out.println("Checking Current CourseList");

            List<Course> allstudentcourses= student.getsCourses();

            allstudentcourses.add(cou);
            student.setsCourses(allstudentcourses);
            entityManager.persist(student);
            entityManager.getTransaction().commit();
            System.out.println();
            System.out.println("Printing Updated Course List");
            //allstudentcourses = getStudentCourses(sEmail);

//            entityManager.close();
//            entityManagerFactory.close();
//        } catch(Exception e) {
//            System.out.println("Error registering student to Course.........."
//                    + "Already Registered.");
//        }
    }



    ////This method takes a Student’s Email as a parameter and would find all
    //	//the courses a student is registered.

    @Override
    public List<Course> getStudentCourses(String sEmail) {
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("SMS");
        EntityManager entityManager = emfactory.createEntityManager();
        try {

            String sql = " SELECT sc FROM Student s JOIN s.sCourses sc WHERE s.sEmail = :Email ";
            //Student foundStudent = entityManager.find(Student.class, sEmail);

            Query query = entityManager.createQuery(sql);
            query.setParameter("Email", sEmail);

            List<Course> allMyCourses = query.getResultList();  //say it is equal to above query
//        System.out.println();
//        System.out.println("My Classes: ");
//        System.out.printf("%-5s|%-25s|%-25s\n", "#", "COURSE NAME", "INSTRUCTOR NAME");
//        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            //return allMyCourses;
            //for(Course course : allMyCourses){
            //System.out.printf("%-5s|%-25s|%-25s \n", course.getcId(), course.getcName(), course.getcInstructorName());

            entityManager.close();
            emfactory.close();
            return allMyCourses;
        } catch (Exception e) {
            return null;
        }


//        entityManager.close();
//        emfactory.close();
        //return allMyCourses;
    }


    }

