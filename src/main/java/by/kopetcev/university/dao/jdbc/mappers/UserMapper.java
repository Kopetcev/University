package by.kopetcev.university.dao.jdbc.mappers;

import by.kopetcev.university.model.Student;
import by.kopetcev.university.model.Teacher;
import by.kopetcev.university.model.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class UserMapper implements RowMapper<User> {

    public static final String USER_ID = "user_id";
    public static final String TEACHER_ID = "teacher_user_id";
    public static final String STUDENT_ID = "student_user_id";
    public static final String STAFF_ID = "staff_user_id";
    public static final String USER_LOGIN = "login";
    public static final String USER_PASSWORD = "password";
    public static final String USER_EMAIL = "email";
    public static final String USER_FIRST_NAME = "first_name";
    public static final String USER_LAST_NAME = "last_name";
    public static final String GROUP_ID = "group_id";

    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        try {
            if(resultSet.getLong(STUDENT_ID)==resultSet.getLong(USER_ID)){
                return new Student(resultSet.getLong(USER_ID),
                        resultSet.getString(USER_LOGIN),
                        resultSet.getString(USER_PASSWORD),
                        resultSet.getString(USER_EMAIL),
                        resultSet.getString(USER_FIRST_NAME),
                        resultSet.getString(USER_LAST_NAME),
                        resultSet.getLong(GROUP_ID));
            }
            if(resultSet.getLong(TEACHER_ID)==resultSet.getLong(USER_ID)){
                 return new Teacher(resultSet.getLong(USER_ID),
                        resultSet.getString(USER_LOGIN),
                        resultSet.getString(USER_PASSWORD),
                        resultSet.getString(USER_EMAIL),
                        resultSet.getString(USER_FIRST_NAME),
                        resultSet.getString(USER_LAST_NAME));
            }

            return new User(resultSet.getLong(USER_ID),
                    resultSet.getString(USER_LOGIN),
                    resultSet.getString(USER_PASSWORD),
                    resultSet.getString(USER_EMAIL),
                    resultSet.getString(USER_FIRST_NAME),
                    resultSet.getString(USER_LAST_NAME));

        } catch (Exception e) {
            throw new RuntimeException("Unable to map row", e);
        }
    }
}
