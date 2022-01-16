package jpa.service;

import jpa.dao.CourseDAO;
import jpa.entitymodels.Course;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class CourseService  implements CourseDAO {
    public CourseService() {

    }
//get's all the courses of the student from the courses table
    @Override
    public List<Course> getAllCourses() {

            EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("SMS");
            EntityManager entityManager =emfactory.createEntityManager();

               // String sql = "SELECT c FROM course c";

        List<Course> courses = (List<Course>) entityManager.createQuery("SELECT c FROM Course c").getResultList();


//                TypedQuery<Course> query = entityManager.createQuery(sql, Course.class);
//
//                List<Course> courses = query.getResultList();

        entityManager.close();
        emfactory.close();

                return courses;

    }


}
