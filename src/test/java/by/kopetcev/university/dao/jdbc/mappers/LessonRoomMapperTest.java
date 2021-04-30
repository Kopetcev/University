package by.kopetcev.university.dao.jdbc.mappers;

import by.kopetcev.university.model.LessonRoom;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LessonRoomMapperTest{

    private static final int rowNum = 1;

    @Mock
    private ResultSet resultSetMock;

    private LessonRoomMapper mapperTest = new LessonRoomMapper();

    @Test
    void shouldThrowExceptionOnNull() {
        assertThrows(RuntimeException.class, () -> mapperTest.mapRow(null,rowNum));
    }

    @Test
    void shouldReturnLessonRoom() throws SQLException {
        when(resultSetMock.getLong("lesson_room_id")).thenReturn(1L);
        when(resultSetMock.getString("lesson_room_name")).thenReturn("TestName");
        assertEquals(new LessonRoom(1L, "TestName"), mapperTest.mapRow(resultSetMock,rowNum));
    }
}
