package by.kopetcev.university.data;

import by.kopetcev.university.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("!test")
public class DataBuilder implements ApplicationRunner {

    private final ApplicationContext applicationContext;
    private final UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(
            DataBuilder.class);

    public DataBuilder(ApplicationContext applicationContext, UserService userService) {
        this.applicationContext = applicationContext;
        this.userService = userService;
    }

    @Override
    public void run(ApplicationArguments args) {
        logger.info("Checking empty of data base");
        if (userService.findAll().isEmpty()) {
            logger.info("Start of adding test data to data base");

            TestData testData = applicationContext.getBean("testData", TestData.class);
            testData.addData();
            logger.info("End of adding test data to data base");
        }
        logger.info("Data base is not empty");
    }
}
