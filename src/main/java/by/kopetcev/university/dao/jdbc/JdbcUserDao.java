package by.kopetcev.university.dao.jdbc;

import by.kopetcev.university.dao.UserDao;
import by.kopetcev.university.dao.jdbc.mappers.UserMapper;
import by.kopetcev.university.exception.DaoException;
import by.kopetcev.university.model.User;
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
public class JdbcUserDao extends AbstractCrudDao<User, Long> implements UserDao {

    public static final String USER_ID = "user_id";

    public static final String STUDENT_ID = "student_user_id";

    public static final String TEACHER_ID = "teacher_user_id";

    public static final String GROUP_ID = "group_id";

    public static final String USER_LOGIN = "login";

    public static final String USER_PASSWORD = "password";

    public static final String USER_EMAIL = "email";

    public static final String USER_FIRST_NAME = "first_name";

    public static final String USER_LAST_NAME = "last_name";

    private static final String TABLE_NAME_USERS = "users";

    private static final String TABLE_NAME_STUDENT = "students";

    private static final String TABLE_NAME_TEACHER = "teachers";

    private static final String UPDATE = "UPDATE users SET login=?, password=?, email=?, first_name=?, last_name=?  WHERE user_id =?";

    private static final String FIND_ALL = "SELECT * FROM users u " +
            "LEFT JOIN students st ON u.user_id = st.student_user_id " +
            "LEFT JOIN teachers ON u.user_id = teachers.teacher_user_id";

    private static final String FIND_BY_ID = "SELECT * FROM users u " +
            "LEFT JOIN students st ON u.user_id = st.student_user_id " +
            "LEFT JOIN teachers th ON u.user_id = th.teacher_user_id " +
            "WHERE u.user_id=?";

    private static final String FIND_ALL_STUDENTS = "SELECT * FROM users u " +
            "LEFT JOIN students st ON u.user_id = st.student_user_id " +
            "LEFT JOIN teachers th ON u.user_id = th.teacher_user_id " +
            "WHERE u.user_id=st.student_user_id";

    private static final String FIND_ALL_TEACHER = "SELECT * FROM users u " +
            "LEFT JOIN students st ON u.user_id = st.student_user_id " +
            "LEFT JOIN teachers th ON u.user_id = th.teacher_user_id " +
            "WHERE u.user_id=th.teacher_user_id";

    private static final String FIND_BY_LOGIN_PASSWORD = "SELECT * FROM users u " +
            "LEFT JOIN students st ON u.user_id = st.student_user_id " +
            "LEFT JOIN teachers th ON u.user_id = th.teacher_user_id " +
            "WHERE u.login = ? AND u.password = ?";

    private static final String DELETE_BY_ID = "DELETE FROM users WHERE user_id =?";

    private static final String DELETE_FROM_STUDENT_BY_ID = "DELETE FROM students WHERE student_user_id =?";

    private static final String DELETE_FROM_TEACHER_BY_ID = "DELETE FROM teachers WHERE teacher_user_id =?";

    private final UserMapper userMapper;

    private final SimpleJdbcInsert userInsert;

    private final SimpleJdbcInsert studentInsert;

    private final SimpleJdbcInsert teacherInsert;

    private static final Logger logger = LoggerFactory.getLogger(
            JdbcUserDao.class);

    @Autowired
    protected JdbcUserDao(DataSource dataSource, UserMapper userMapper) {
        super(dataSource);
        this.userMapper = userMapper;
        this.userInsert = new SimpleJdbcInsert(jdbcTemplate).withTableName(TABLE_NAME_USERS).usingGeneratedKeyColumns(USER_ID);
        this.studentInsert = new SimpleJdbcInsert(jdbcTemplate).withTableName(TABLE_NAME_STUDENT);
        this.teacherInsert = new SimpleJdbcInsert(jdbcTemplate).withTableName(TABLE_NAME_TEACHER);
    }

    @Override
    protected User create(User entity) {
        try {
            Map<String, Object> params = new HashMap<>();
            params.put(USER_LOGIN, entity.getLogin());
            params.put(USER_PASSWORD, entity.getPassword());
            params.put(USER_EMAIL, entity.getEmail());
            params.put(USER_FIRST_NAME, entity.getFirstName());
            params.put(USER_LAST_NAME, entity.getLastName());
            Number id = userInsert.executeAndReturnKey(params);
            logger.debug("Created a new user with login = {}, email = {}, first name = {}, last name = {}",entity.getLogin(), entity.getEmail(), entity.getFirstName(), entity.getLastName());

            return new User(id.longValue(), entity.getLogin(), entity.getPassword(), entity.getEmail(), entity.getFirstName(), entity.getLastName());
        } catch (DataAccessException e) {
            logger.debug("Unable to create a new user with login = {}, email = {}, first name = {}, last name = {}",entity.getLogin(), entity.getEmail(), entity.getFirstName(), entity.getLastName());
            throw new DaoException("Unable to create a new user", e);
        }
    }

    @Override
    protected User update(User entity) {
        if (jdbcTemplate.update(UPDATE, entity.getLogin(), entity.getPassword(), entity.getEmail(), entity.getFirstName(), entity.getLastName(), entity.getId()) == 1) {
            logger.debug("Updated user with id = {}", entity.getId());
            return new User(entity.getId(), entity.getLogin(), entity.getPassword(), entity.getEmail(), entity.getFirstName(), entity.getLastName());
        } else {
            logger.warn("Unable to update user with id = {}, login = {}, email = {}, first name = {}, last name = {}", entity.getId(), entity.getLogin(), entity.getEmail(), entity.getFirstName(), entity.getLastName());
            throw new DaoException("Unable to update user with id=" + entity.getId());
        }
    }

    @Override
    public Optional<User> findById(Long id) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_ID, userMapper, id));
        } catch (EmptyResultDataAccessException e) {
            logger.debug("User with id = {} not found", id);
            return Optional.empty();
        }
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query(FIND_ALL, userMapper);
    }

    @Override
    public boolean deleteById(Long id) {
        return jdbcTemplate.update(DELETE_BY_ID, id) == 1;
    }

    @Override
    public Optional<User> findByLoginPassword(String login, String password) {
    try {
        return Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_LOGIN_PASSWORD, userMapper, login, password));
    } catch (EmptyResultDataAccessException e) {
        logger.debug("User with this login and password not found");
        return Optional.empty();
    }
}

    @Override
    public boolean assignStudent(Long userId, Long groupId) {
        Map<String, Object> params = new HashMap<>();
                params.put(STUDENT_ID, userId);
                params.put(GROUP_ID, groupId);
        if (studentInsert.execute(params) == 1) {
            logger.debug("Assigned student to user with id = {} with group with  id {}", userId, groupId);
            return true;
        } else {
            logger.debug("Unable to assign student to user with id = {} with group with  id {}", userId, groupId);
            throw new DaoException("Unable to assign student to user with id = " + userId);
        }
    }

    @Override
    public boolean assignTeacher(Long userId) {
        Map<String, Object> params = new HashMap<>();
        params.put(TEACHER_ID, userId);
        if (teacherInsert.execute(params) == 1) {
            logger.debug("Assigned teacher to user with id = {}", userId);
            return true;
        } else {
            logger.debug("Unable to assign teacher to user with id = {}", userId);
            throw new DaoException("Unable to assign teacher to user with id = " + userId);
        }
    }

    @Override
    public boolean deleteFromTeacher(Long teacherId) {
        return jdbcTemplate.update(DELETE_FROM_TEACHER_BY_ID, teacherId) == 1;
    }

    @Override
    public boolean deleteFromStudent(Long studentId) {
        return jdbcTemplate.update(DELETE_FROM_STUDENT_BY_ID, studentId) == 1;
    }

    @Override
    public List<User> findAllTeacher() {
        return jdbcTemplate.query(FIND_ALL_TEACHER, userMapper);
    }

    @Override
    public List<User> findAllStudent() {
        return jdbcTemplate.query(FIND_ALL_STUDENTS, userMapper);
    }



}
