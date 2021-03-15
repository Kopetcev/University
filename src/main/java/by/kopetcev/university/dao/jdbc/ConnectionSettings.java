package by.kopetcev.university.dao.jdbc;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ConnectionSettings {

    private static int DEFAULT_MAX_POOL_SIZE = 5;

    @Value("${db.jdbcDriver}")
    private String jdbcDriver;
    @Value("${db.jdbcUrl}")
    private String jdbcUrl;
    @Value("${db.jdbcUser}")
    private String jdbcUser;
    @Value("${db.jdbcPassword}")
    private String jdbcPassword;
    private int jdbcMaxPoolSize = DEFAULT_MAX_POOL_SIZE;

    public static int getDefaultMaxPoolSize() {
        return DEFAULT_MAX_POOL_SIZE;
    }

    public String getJdbcDriver() {
        return jdbcDriver;
    }

    public String getJdbcUrl() {
        return jdbcUrl;
    }

    public String getJdbcUser() {
        return jdbcUser;
    }

    public String getJdbcPassword() {
        return jdbcPassword;
    }

    public int getJdbcMaxPoolSize() {
        return jdbcMaxPoolSize;
    }
}