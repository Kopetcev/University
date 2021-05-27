package by.kopetcev.university.dao.jdbc.mappers;

import by.kopetcev.university.model.Lesson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class LessonMapper implements RowMapper<Lesson> {

    private static final String LESSON_ID = "lesson_id";

    private static final String COURSE_ID = "course_id";

    private static final String GROUP_ID = "group_id";

    private static final String TEACHER_ID = "teacher_id";

    private static final String DAY_OF_WEEK = "day_of_week_id";

    private static final String LESSON_TIME_ID = "lesson_time_id";

    private static final String LESSON_ROOM_ID = "lesson_room_id";

    private static final Logger logger = LoggerFactory.getLogger(
            LessonMapper.class);

    @Override
    public Lesson mapRow(ResultSet resultSet, int i) throws SQLException {
        try {
            return new Lesson(resultSet.getLong(LESSON_ID), resultSet.getLong(COURSE_ID), resultSet.getLong(GROUP_ID),
                    resultSet.getLong(TEACHER_ID), resultSet.getLong(DAY_OF_WEEK), resultSet.getLong(LESSON_TIME_ID), resultSet.getLong(LESSON_ROOM_ID));

        } catch (Exception e) {
            logger.warn("Unable to map lesson", e);
            throw new RuntimeException("Unable to map row", e);
        }
    }
}

