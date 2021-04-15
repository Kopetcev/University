package by.kopetcev.university.dao.jdbc.mappers;

import by.kopetcev.university.model.Staff;
import by.kopetcev.university.model.Student;
import by.kopetcev.university.model.Teacher;
import by.kopetcev.university.model.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

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
    public static final String USER_LAST_NAME = "first_name";
    public static final String ROLES = "roles";
    public static final String GROUP_ID = "group_id";
    public static final String COURSES = "courses";

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
                        Set.of((Integer[])resultSet.getArray(ROLES).getArray()),
                        resultSet.getLong(GROUP_ID));
            }
            if(resultSet.getLong(TEACHER_ID)==resultSet.getLong(USER_ID)){
                 return new Teacher(resultSet.getLong(USER_ID),
                        resultSet.getString(USER_LOGIN),
                        resultSet.getString(USER_PASSWORD),
                        resultSet.getString(USER_EMAIL),
                        resultSet.getString(USER_FIRST_NAME),
                        resultSet.getString(USER_LAST_NAME),
                        Set.of((Integer[])resultSet.getArray(ROLES).getArray()),
                        Set.of((Integer[])resultSet.getArray(COURSES).getArray()));
            }
            if(resultSet.getLong(STAFF_ID)==resultSet.getLong(USER_ID)) {
                return new Staff(resultSet.getLong(USER_ID),
                        resultSet.getString(USER_LOGIN),
                        resultSet.getString(USER_PASSWORD),
                        resultSet.getString(USER_EMAIL),
                        resultSet.getString(USER_FIRST_NAME),
                        resultSet.getString(USER_LAST_NAME),
                        Set.of((Integer[]) resultSet.getArray(ROLES).getArray()));
            }

            return new User(resultSet.getLong(USER_ID),
                    resultSet.getString(USER_LOGIN),
                    resultSet.getString(USER_PASSWORD),
                    resultSet.getString(USER_EMAIL),
                    resultSet.getString(USER_FIRST_NAME),
                    resultSet.getString(USER_LAST_NAME),
                    Set.of((Integer[]) resultSet.getArray(ROLES).getArray()));

        } catch (Exception e) {
            throw new RuntimeException("Unable to map row", e);
        }
    }
}
