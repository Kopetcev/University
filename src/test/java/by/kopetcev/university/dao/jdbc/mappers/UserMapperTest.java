package by.kopetcev.university.dao.jdbc.mappers;

import by.kopetcev.university.model.Student;
import by.kopetcev.university.model.Teacher;
import by.kopetcev.university.model.User;
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
class UserMapperTest{
    private static final int rowNum = 1;

    @Mock
    private ResultSet resultSetMock;

    private UserMapper mapperTest = new UserMapper();

    @Test
    void shouldThrowExceptionOnNull() {
        assertThrows(RuntimeException.class, () -> mapperTest.mapRow(null,rowNum));
    }

    @Test
    void shouldReturnUser() throws SQLException {
        when(resultSetMock.getLong("user_id")).thenReturn(1L);
        when(resultSetMock.getString("login")).thenReturn("TestLogin");
        when(resultSetMock.getString("password")).thenReturn("TestPassword");
        when(resultSetMock.getString("email")).thenReturn("TestEmail@mail.com");
        when(resultSetMock.getString("first_name")).thenReturn("TestName");
        when(resultSetMock.getString("last_name")).thenReturn("TestLastName");
        when(resultSetMock.getLong("teacher_user_id")).thenReturn(2L);
        when(resultSetMock.getLong("student_user_id")).thenReturn(2L);
        assertEquals(new User(1L, "TestLogin", "TestPassword", "TestEmail@mail.com", "TestName", "TestLastName"), mapperTest.mapRow(resultSetMock,rowNum));
    }

    @Test
    void shouldReturnStudent() throws SQLException {
        when(resultSetMock.getLong("user_id")).thenReturn(1L);
        when(resultSetMock.getString("login")).thenReturn("TestLogin");
        when(resultSetMock.getString("password")).thenReturn("TestPassword");
        when(resultSetMock.getString("email")).thenReturn("TestEmail@mail.com");
        when(resultSetMock.getString("first_name")).thenReturn("TestName");
        when(resultSetMock.getString("last_name")).thenReturn("TestLastName");
        when(resultSetMock.getLong("student_user_id")).thenReturn(1L);
        when(resultSetMock.getLong("group_id")).thenReturn(1L);
        assertEquals(new Student(1L, "TestLogin", "TestPassword", "TestEmail@mail.com", "TestName", "TestLastName", 1L), mapperTest.mapRow(resultSetMock,rowNum));
    }

    @Test
    void shouldReturnTeacher() throws SQLException {
        when(resultSetMock.getLong("user_id")).thenReturn(1L);
        when(resultSetMock.getString("login")).thenReturn("TestLogin");
        when(resultSetMock.getString("password")).thenReturn("TestPassword");
        when(resultSetMock.getString("email")).thenReturn("TestEmail@mail.com");
        when(resultSetMock.getString("first_name")).thenReturn("TestName");
        when(resultSetMock.getString("last_name")).thenReturn("TestLastName");
        when(resultSetMock.getLong("teacher_user_id")).thenReturn(1L);
        when(resultSetMock.getLong("student_user_id")).thenReturn(2L);
        assertEquals(new Teacher(1L, "TestLogin", "TestPassword", "TestEmail@mail.com", "TestName", "TestLastName"), mapperTest.mapRow(resultSetMock,rowNum));
    }



}
