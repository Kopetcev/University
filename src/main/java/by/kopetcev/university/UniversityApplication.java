package by.kopetcev.university;

import by.kopetcev.university.dao.jdbc.JdbcCourseDao;
import by.kopetcev.university.dao.jdbc.JdbcUserDao;
import by.kopetcev.university.model.Course;
import by.kopetcev.university.model.Teacher;
import by.kopetcev.university.model.User;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.undertow.UndertowServletWebServer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Set;

@SpringBootApplication
public class UniversityApplication {

    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        JdbcCourseDao courseDao = (JdbcCourseDao) context.getBean("jdbcCourseDao");

        JdbcUserDao userDao = context.getBean("jdbcUserDao", JdbcUserDao.class);


/*
       TestCrud testDao = context.getBean("testCrud", TestCrud.class);

         Set<Long>  set = testDao.findId(1l);

         for(Long number: set){
             System.out.println(number);
         }

 */
        User user =  userDao.findById(1L).get();
        System.out.println(user.getClass());

        Teacher teacher = (Teacher)user;

       System.out.println( userDao.findById(1L).get().getLogin());


        //Course course = courseDao.save(new Course("MATAN"));

        //System.out.println(courseDao.findById(course.getId()).get().getName());

        //courseDao.deleteById(course.getId());
    }
}





