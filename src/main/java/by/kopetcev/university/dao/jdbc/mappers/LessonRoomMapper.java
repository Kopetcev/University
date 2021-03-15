package by.kopetcev.university.dao.jdbc.mappers;

import by.kopetcev.university.model.LessonRoom;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class LessonRoomMapper implements RowMapper<LessonRoom> {

    public static final String LESSON_ROOM_ID = "lesson_room_id";
    public static final String LESSON_ROOM_NAME = "lesson_room_name";

    @Override
    public LessonRoom mapRow(ResultSet resultSet, int i) {
        try {
            return new LessonRoom(resultSet.getLong(LESSON_ROOM_ID), resultSet.getString(LESSON_ROOM_NAME));

        } catch (Exception e) {
            throw new RuntimeException("Unable to map row", e);
        }
    }
}
