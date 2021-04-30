package by.kopetcev.university.dao.jdbc;

import by.kopetcev.university.dao.jdbc.mappers.LessonRoomMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import javax.sql.DataSource;

@TestConfiguration
@Import(DataSourceTestConfig.class)
@ComponentScan("by.kopetcev.university.dao.jdbc.mappers")
public class JdbcLessonRoomDaoTestConfig {

    @Bean
    @Autowired
    JdbcLessonRoomDao jdbcLessonRoomDao(DataSource dataSource, LessonRoomMapper lessonRoomMapper){
        return new JdbcLessonRoomDao(dataSource, lessonRoomMapper);
    }
}