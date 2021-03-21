package by.kopetcev.university;

import by.kopetcev.university.dao.jdbc.JdbcCourseDao;
import by.kopetcev.university.model.Course;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class UniversityApplication {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        JdbcCourseDao courseDao = (JdbcCourseDao) context.getBean("jdbcCourseDao");
        Course course = courseDao.save(new Course("MATAN"));

        System.out.println(courseDao.findById(course.getId()).get().getName());

        courseDao.deleteById(course.getId());

    }
}





