package by.kopetcev.university;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class UniversityApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(UniversityApplication.class, args);
    }
}
