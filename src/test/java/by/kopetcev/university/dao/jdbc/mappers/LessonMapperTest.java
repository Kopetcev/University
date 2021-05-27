package by.kopetcev.university.dao.jdbc.mappers;

import by.kopetcev.university.model.Lesson;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LessonMapperTest {

    private static final int rowNum = 1;

    @Mock
    private ResultSet resultSetMock;

    private LessonMapper mapperTest = new LessonMapper();

    @Test
    void shouldThrowExceptionOnNull() {
        assertThrows(RuntimeException.class, () -> mapperTest.mapRow(null, rowNum));
    }

    @Test
    void shouldReturnLesson() throws SQLException {
        when(resultSetMock.getLong("lesson_id")).thenReturn(1L);
        when(resultSetMock.getLong("course_id")).thenReturn(1L);
        when(resultSetMock.getLong("group_id")).thenReturn(1L);
        when(resultSetMock.getLong("teacher_id")).thenReturn(1L);
        when(resultSetMock.getLong("day_of_week_id")).thenReturn(1L);
        when(resultSetMock.getLong("lesson_time_id")).thenReturn(1L);
        when(resultSetMock.getLong("lesson_room_id")).thenReturn(1L);
        assertEquals(new Lesson(1L, 1L, 1L, 1L, 1L, 1L, 1L), mapperTest.mapRow(resultSetMock, rowNum));
    }
}
