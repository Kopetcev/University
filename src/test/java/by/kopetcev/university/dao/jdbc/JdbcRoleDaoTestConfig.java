package by.kopetcev.university.dao.jdbc;

import by.kopetcev.university.dao.jdbc.mappers.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import javax.sql.DataSource;

@TestConfiguration
@Import(DataSourceTestConfig.class)
@ComponentScan("by.kopetcev.university.dao.jdbc.mappers")
public class JdbcRoleDaoTestConfig {

    @Bean
    @Autowired
    JdbcRoleDao jdbcRoleDao(DataSource dataSource, RoleMapper roleMapper){
        return new JdbcRoleDao(dataSource, roleMapper);
    }
}

