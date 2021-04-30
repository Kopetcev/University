package by.kopetcev.university.dao.jdbc.mappers;

import by.kopetcev.university.model.Role;
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
public class RoleMapperTest{

    private static final int rowNum = 1;

    @Mock
    private ResultSet resultSetMock;

    private RoleMapper mapperTest = new RoleMapper();

    @Test
    void shouldThrowExceptionOnNull() {
        assertThrows(RuntimeException.class, () -> mapperTest.mapRow(null,rowNum));
    }

    @Test
    void shouldReturnRole() throws SQLException {
        when(resultSetMock.getLong("role_id")).thenReturn(1L);
        when(resultSetMock.getString("role_name")).thenReturn("TestName");
        assertEquals(new Role(1L, "TestName"), mapperTest.mapRow(resultSetMock,rowNum));
    }
}


