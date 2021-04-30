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

import java.time.LocalTime;
import java.util.Set;

@SpringBootApplication
public class UniversityApplication {

    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        System.out.println(LocalTime.of(10,00).toString());


    }
}





