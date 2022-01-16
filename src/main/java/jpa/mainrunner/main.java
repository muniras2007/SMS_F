//package jpa.entitymodels;
//
//import javax.management.Query;
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.Persistence;
//import java.util.List;
//
//
//public class Main {
//    public List<Course> getAllCourses() {
//        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("SMSPersistenceUnit");
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//
//        List<Course> courses = entityManager.createQuery("select c from Course c", Course.class).getResultList();
//
//        for (Course currentCourse : courses) {
//            System.out.println(currentCourse.getcName() + currentCourse.getStudents());
//
//
//        }
//        entityManagerFactory.close();
//        entityManager.close();
//
//        return courses;
//    }
//
//    //******************************************************
//
//    public Course getCourseById(int id) {
//            Course course
//
//        public static void main (String[]args){
//
//
//        }
//    }
//}
//
//
//
//
