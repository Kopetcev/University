package by.kopetcev.university.dao.jdbc;

import by.kopetcev.university.dao.jdbc.mappers.LessonRoomMapper;
import by.kopetcev.university.dao.jdbc.mappers.LessonTimeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import javax.sql.DataSource;

@TestConfiguration
@Import(DataSourceTestConfig.class)
@ComponentScan("by.kopetcev.university.dao.jdbc.mappers")
public class JdbcLessonTimeDaoTestConfig {

    @Bean
    @Autowired
    JdbcLessonTimeDao jdbcLessonTimeDaoTest(DataSource dataSource, LessonTimeMapper lessonTimeMapper){
        return new JdbcLessonTimeDao(dataSource, lessonTimeMapper);
    }
}
