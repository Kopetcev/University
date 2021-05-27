package by.kopetcev.university.dao.jdbc.mappers;

import by.kopetcev.university.model.LessonRoom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;

@Component
public class LessonRoomMapper implements RowMapper<LessonRoom> {

    public static final String LESSON_ROOM_ID = "lesson_room_id";
    public static final String LESSON_ROOM_NAME = "lesson_room_name";
    private static final Logger logger = LoggerFactory.getLogger(
            LessonRoomMapper.class);

    @Override
    public LessonRoom mapRow(ResultSet resultSet, int i) {
        try {
            return new LessonRoom(resultSet.getLong(LESSON_ROOM_ID), resultSet.getString(LESSON_ROOM_NAME));

        } catch (Exception e) {
            logger.warn("Unable to map lessonRoom", e);
            throw new RuntimeException("Unable to map row", e);
        }
    }
}
