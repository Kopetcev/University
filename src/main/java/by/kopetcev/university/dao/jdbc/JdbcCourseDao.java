package by.kopetcev.university.dao.jdbc;

import by.kopetcev.university.dao.CourseDao;
import by.kopetcev.university.dao.jdbc.mappers.CourseMapper;
import by.kopetcev.university.exception.DaoException;
import by.kopetcev.university.model.Course;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class JdbcCourseDao extends AbstractCrudDao<Course, Long> implements CourseDao {

    private static final String TABLE_COURSES = "courses";

    private static final String TABLE_TEACHER_COURSE = "teacher_courses";

    private static final String COURSE_ID = "course_id";

    private static final String COURSE_NAME = "course_name";

    private static final String TEACHER_ID = "teacher_id";

    private static final String UPDATE = "UPDATE courses SET course_name=? WHERE course_id =?";

    private static final String FIND_ALL = "SELECT course_id, course_name FROM courses";

    private static final String FIND_BY_ID = "SELECT course_id, course_name FROM courses WHERE course_id = ?";

    private static final String DELETE_BY_ID = "DELETE FROM courses WHERE course_id =?";

    private static final String DELETE_BY_ID_FROM_TEACHER = "DELETE FROM teacher_courses WHERE course_id =? AND teacher_id = ?";

    private static final String FIND_BY_TEACHER_ID = "SELECT courses.course_id, course_name FROM courses,teacher_courses where courses.course_id = teacher_courses.course_id AND teacher_courses.teacher_id = ?";

    private final CourseMapper courseMapper;

    private final SimpleJdbcInsert courseInsert;

    private final SimpleJdbcInsert teacherCourseInsert;

    private static final Logger logger = LoggerFactory.getLogger(
            JdbcCourseDao.class);


    @Autowired
    protected JdbcCourseDao(DataSource dataSource, CourseMapper courseMapper) {
        super(dataSource);
        this.courseMapper = courseMapper;
        this.courseInsert = new SimpleJdbcInsert(jdbcTemplate).withTableName(TABLE_COURSES).usingGeneratedKeyColumns(COURSE_ID);
        this.teacherCourseInsert = new SimpleJdbcInsert(jdbcTemplate).withTableName(TABLE_TEACHER_COURSE);
    }

    @Override
    protected Course create(Course entity) {
        try {
            Map<String, Object> params = new HashMap<>();
            params.put(COURSE_NAME, entity.getName());
            Number id = courseInsert.executeAndReturnKey(params);
            logger.debug("Created a new course with course name = {}", entity.getName() );
            return new Course(id.longValue(), entity.getName());
        } catch (DataAccessException e) {
            logger.warn("Unable to create a new course with course name = {}", entity.getName());
            throw new DaoException("Unable to create a new course", e);
        }
    }

    @Override
    protected Course update(Course entity) {
        if (jdbcTemplate.update(UPDATE, entity.getName(), entity.getId()) == 1) {
            logger.debug("Updated course with id = {}", entity.getId());
            return new Course(entity.getId(), entity.getName());
        } else {
            logger.warn("Unable to update course with id = {}", entity.getId());
            throw new DaoException("Unable to update course with id = " + entity.getId());
        }
    }

    @Override
    public Optional<Course> findById(Long id) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_ID, courseMapper, id));
        } catch (EmptyResultDataAccessException e) {
            logger.debug("Course with id = {} not found", id, e);
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

    @Override
    public boolean assignTeacher(Long courseId, Long teacherId) {
        Map<String, Object> params = new HashMap<>();
        params.put(COURSE_ID, courseId);
        params.put(TEACHER_ID, teacherId);
        if (teacherCourseInsert.execute(params) == 1) {
            logger.debug("Assigned teacher with id = {} to course with id ={}", teacherId, courseId);
            return true;
        } else {
            logger.warn("Unable to assign teacher with id = {} to course with id ={}", teacherId, courseId);
            throw new DaoException("Unable to assign teacher with id = " + teacherId + " to course with id = " + courseId);
        }
    }

    @Override
    public List<Course> findByTeacherId(Long teacherId) {
        return jdbcTemplate.query(FIND_BY_TEACHER_ID, courseMapper, teacherId);
    }

    @Override
    public boolean deleteByIdFromTeacher(Long courseId, Long teacherId) {
        return jdbcTemplate.update(DELETE_BY_ID_FROM_TEACHER, courseId, teacherId) == 1;
    }

}
