package by.kopetcev.university.dao.jdbc.mappers;

import by.kopetcev.university.model.LessonTime;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LessonTimeMapperTest{
    private static final int rowNum = 1;

    @Mock
    private ResultSet resultSetMock;

    private LessonTimeMapper mapperTest = new LessonTimeMapper();

    @Test
    void shouldThrowExceptionOnNull() {
        assertThrows(RuntimeException.class, () -> mapperTest.mapRow(null,rowNum));
    }

    @Test
    void shouldReturnLessonTime() throws SQLException {
        when(resultSetMock.getLong("lesson_time_id")).thenReturn(1L);
        when(resultSetMock.getTime("lesson_start")).thenReturn(new Time(9,0,0));
        when(resultSetMock.getTime("lesson_end")).thenReturn(new Time(10,0,0));
        assertEquals(new LessonTime(1L, LocalTime.of(9,0), LocalTime.of(10,0)), mapperTest.mapRow(resultSetMock,rowNum));
    }
}