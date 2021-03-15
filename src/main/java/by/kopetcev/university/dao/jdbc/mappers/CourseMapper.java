package by.kopetcev.university.dao.jdbc.mappers;

import by.kopetcev.university.model.Course;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.function.Function;

@Component
public class CourseMapper implements RowMapper<Course> {

    public static final String COURSE_ID = "course_id";
    public static final String COURSE_NAME = "course_name";

    @Override
    public Course mapRow(ResultSet resultSet, int i) throws SQLException {
        try {
            return new Course(resultSet.getLong(COURSE_ID), resultSet.getString(COURSE_NAME));

        } catch (Exception e) {
            throw new RuntimeException("Unable to map row", e);
        }
    }
}
