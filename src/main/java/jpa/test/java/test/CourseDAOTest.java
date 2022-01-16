package test;

import jpa.entitymodels.Course;
import jpa.service.CourseService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
//import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class CourseDAOTest {



     CourseService courseService;

    @Before
    public void before(){
        //create a new instance of course service class so you can test it
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("SMS");
        EntityManager entityManager =emfactory.createEntityManager();

        courseService= new CourseService();

    }
    @Test
    public void getAllCoursesTest(){
        List<Course> courses = courseService.getAllCourses();
        Assert.assertEquals(courses.size(),10);


    }
}
