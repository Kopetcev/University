package by.kopetcev.university.dao.jdbc;

import by.kopetcev.university.dao.UserDao;
import by.kopetcev.university.dao.jdbc.mappers.UserMapper;
import by.kopetcev.university.exception.DaoException;
import by.kopetcev.university.model.User;
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

    private static final String TABLE_NAME = "users";

    public static final String USER_ID = "user_id";

    public static final String USER_LOGIN = "login";

    public static final String USER_PASSWORD = "password";

    public static final String USER_EMAIL = "email";

    public static final String USER_FIRST_NAME = "first_name";

    public static final String USER_LAST_NAME = "last_name";

    private static final String UPDATE = "UPDATE users SET login=?, password=?, email=?, first_name=?, last_name=?  WHERE user_id =?";

    private static final String FIND_ALL = "SELECT * FROM users u " +
            "LEFT JOIN students st ON u.user_id = st.student_user_id " +
            "LEFT JOIN teachers ON u.user_id = teachers.teacher_user_id ";

    private static final String FIND_BY_ID = "SELECT * FROM users u " +
            "LEFT JOIN students st ON u.user_id = st.student_user_id " +
            "LEFT JOIN teachers ON u.user_id = teachers.teacher_user_id " +
            "WHERE u.user_id=?";

    private static final String DELETE_BY_ID = "DELETE FROM users WHERE user_id =?";

    private final UserMapper userMapper;

    private final SimpleJdbcInsert userInsert;

    @Autowired
    protected JdbcUserDao(DataSource dataSource, UserMapper userMapper) {
        super(dataSource);
        this.userMapper = userMapper;
        this.userInsert = new SimpleJdbcInsert(jdbcTemplate).withTableName(TABLE_NAME).usingGeneratedKeyColumns(USER_ID);
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
            return new User(id.longValue(), entity.getLogin(), entity.getPassword(), entity.getEmail(), entity.getFirstName(), entity.getLastName());
        } catch (DataAccessException e) {
            throw new DaoException("Unable to create a new user", e);
        }
    }

    @Override
    protected User update(User entity) {
        if (jdbcTemplate.update(UPDATE, entity.getLogin(), entity.getPassword(), entity.getEmail(), entity.getFirstName(), entity.getLastName(), entity.getId()) == 1) {
            return new User(entity.getId(), entity.getLogin(), entity.getPassword(), entity.getEmail(), entity.getFirstName(), entity.getLastName());
        } else {
            throw new DaoException("Unable to update user with id=" + entity.getId());
        }
    }

    @Override
    public Optional<User> findById(Long id) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_ID, userMapper, id));
        } catch (EmptyResultDataAccessException e) {
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
    public Long findByLoginPassword(String login, String password) {
        return jdbcTemplate.queryForObject(
                "select last_name from t_actor where id = ?",
                Long.class, login, password);
    }
}

