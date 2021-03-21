package by.kopetcev.university.dao.jdbc.mappers;

import by.kopetcev.university.model.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

@Component
public class UserMapper implements RowMapper<User> {

    public static final String USER_ID = "user_id";
    public static final String USER_LOGIN = "login";
    public static final String USER_PASSWORD = "password";
    public static final String USER_EMAIL = "email";
    public static final String USER_FIRST_NAME = "first_name";
    public static final String USER_LAST_NAME = "first_name";
    public static final String USER_ROLES = "roles";

    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        try {
            Set<Long> idRoles = Set.of((Long[])resultSet.getArray(USER_ROLES).getArray());
            return new User(resultSet.getLong(USER_ID),
                resultSet.getString(USER_LOGIN),
                resultSet.getString(USER_PASSWORD),
                resultSet.getString(USER_EMAIL),
                resultSet.getString(USER_FIRST_NAME),
                resultSet.getString(USER_LAST_NAME),
                    idRoles);

        } catch (Exception e) {
            throw new RuntimeException("Unable to map row", e);
        }
    }
}
