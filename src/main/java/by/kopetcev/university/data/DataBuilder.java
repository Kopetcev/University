package by.kopetcev.university.data;

import by.kopetcev.university.service.UserService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class DataBuilder implements ApplicationRunner {

    private final ApplicationContext applicationContext;
    private final UserService userService;

    public DataBuilder(ApplicationContext applicationContext, UserService userService) {
        this.applicationContext = applicationContext;
        this.userService = userService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (userService.findAll().isEmpty()) {
            TestData testData = applicationContext.getBean("testData", TestData.class);
            testData.addData();
        }
    }
}
