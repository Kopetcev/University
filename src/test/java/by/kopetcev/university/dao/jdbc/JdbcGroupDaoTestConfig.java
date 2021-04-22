package by.kopetcev.university.dao.jdbc;

import by.kopetcev.university.dao.jdbc.mappers.GroupMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import javax.sql.DataSource;

@TestConfiguration
@Import(DataSourceTestConfig.class)
@ComponentScan("by.kopetcev.university.dao.jdbc.mappers")
public class JdbcGroupDaoTestConfig {

    @Bean
    @Autowired
    JdbcGroupDao jdbcGroupDao(DataSource dataSource, GroupMapper groupMapper){
        return new JdbcGroupDao(dataSource, groupMapper);
    }
}
