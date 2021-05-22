package by.kopetcev.university.dao.jdbc.mappers;

import by.kopetcev.university.model.LessonTime;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;


@Component
public class LessonTimeMapper implements RowMapper<LessonTime> {

    public static final String LESSON_TIME_ID = "lesson_time_id";
    public static final String LESSON_TIME_START = "lesson_start";
    public static final String LESSON_TIME_END = "lesson_end";

    @Override
    public LessonTime mapRow(ResultSet resultSet, int i) {
        try {
            return new LessonTime(resultSet.getLong(LESSON_TIME_ID), resultSet.getTime(LESSON_TIME_START).toLocalTime(), resultSet.getTime(LESSON_TIME_END).toLocalTime());
        } catch (Exception e) {
            throw new RuntimeException("Unable to map row", e);
        }
    }
}
