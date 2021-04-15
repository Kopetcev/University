package by.kopetcev.university.dao.jdbc;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.zaxxer.hikari.HikariConfig;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;

@Sql({ "/drop_schema.sql", "/create_schema.sql" })
public class JdbcCourseDaoTest{

    
    @Autowired
    private JdbcCourseDao tester;

    @BeforeAll
    static void setUp() throws SQLException {
        ConnectionSettings settings = new ConnectionSettings();
        DatabaseConfig config = new  DatabaseConfig(settings);
        HikariConfig hikariConfig = new HikariConfig();
        DataSource dataSource = config.getDataSource(hikariConfig);

    }

    @AfterAll
    static void tearDown() throws IOException {
        dataSource.close();
    }


    @Sql("/sql/insert_JdbcCourseDao.sql")
    @Test
    public void fetchRows2() {

        assertEquals(5, 5);
    }
}
