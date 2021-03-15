package by.kopetcev.university.dao.jdbc;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;


@Configuration
public class DatabaseConfig {

    private final ConnectionSettings connectionSettings;
    private final String POOL_NAME = "UniversityConnectionPool";

    @Autowired
    public DatabaseConfig(ConnectionSettings connectionSettings) {
        this.connectionSettings = connectionSettings;
    }

    @Bean
    @Autowired
    public DataSource getDataSource(HikariConfig hikariConfig) {
        hikariConfig.setDriverClassName(connectionSettings.getJdbcDriver());
        hikariConfig.setJdbcUrl(connectionSettings.getJdbcUrl());
        hikariConfig.setUsername(connectionSettings.getJdbcUser());
        hikariConfig.setPassword(connectionSettings.getJdbcPassword());
        hikariConfig.setMaximumPoolSize(connectionSettings.getJdbcMaxPoolSize());
        hikariConfig.setPoolName(POOL_NAME);
        return new HikariDataSource(hikariConfig);
    }
}