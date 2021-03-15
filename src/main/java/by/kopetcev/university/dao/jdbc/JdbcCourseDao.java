package by.kopetcev.university.dao.jdbc;

import by.kopetcev.university.dao.CourseDao;
import by.kopetcev.university.dao.jdbc.mappers.CourseMapper;
import by.kopetcev.university.exception.DaoException;
import by.kopetcev.university.model.Course;
import org.postgresql.util.PSQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;

@Repository
public class JdbcCourseDao extends AbstractCrudDao<Course, Long> implements CourseDao {

    private static final String TABLE_NAME = "courses";

    private static final String COURSE_ID ="course_id";

    private static final String COURSE_NAME = "course_name";

    private static final String CREATE = "INSERT INTO courses (course_name) VALUES (?)";

    private static final String UPDATE = "UPDATE courses SET course_name=? WHERE course_id =?";

    private static final String FIND_ALL = "SELECT course_id, course_name FROM courses";

    private static final String FIND_BY_ID = "SELECT course_id, course_name FROM courses WHERE course_id = ?";

    private static final String DELETE_BY_ID = "DELETE FROM courses WHERE course_id =?";

    private CourseMapper courseMapper;

    @Autowired
    protected JdbcCourseDao(DataSource dataSource, CourseMapper courseMapper) {
        super(dataSource);
        this.courseMapper = courseMapper;
    }

    @Override
    protected Course create(Course entity) {
        try {
            SimpleJdbcInsert insertCourse = new SimpleJdbcInsert(jdbcTemplate).withTableName(TABLE_NAME).usingGeneratedKeyColumns(COURSE_ID);
            Map<String, Object> params = new HashMap<>();
            params.put(COURSE_NAME, entity.getName());
            Number id = insertCourse.executeAndReturnKey(params);
            return new Course(id.longValue(), entity.getName());
        } catch (DataAccessException e) {
            throw new DaoException("Unable to create a new course");
        }
    }

    @Override
    protected Course update(Course entity) {
        if (jdbcTemplate.update(UPDATE, entity.getName(), entity.getId()) == 1) {
            return new Course(entity.getId(), entity.getName());
        } else {
            throw new DaoException("Unable to update course with id=" + entity.getId());
        }
    }

    @Override
    public Optional<Course> findById(Long id) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_ID, courseMapper, id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Course> findAll() {
        return jdbcTemplate.query(FIND_ALL, courseMapper);
    }

    @Override
    public boolean deleteById(Long id) {
        return jdbcTemplate.update(DELETE_BY_ID, id) == 1;
    }
}
