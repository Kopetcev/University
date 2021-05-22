package by.kopetcev.university.dao.jdbc.mappers;

import by.kopetcev.university.model.Group;
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
class GroupMapperTest {

    private static final int rowNum = 1;

    @Mock
    private ResultSet resultSetMock;

    private GroupMapper mapperTest = new GroupMapper();

    @Test
    void shouldThrowExceptionOnNull() {
        assertThrows(RuntimeException.class, () -> mapperTest.mapRow(null, rowNum));
    }

    @Test
    void shouldReturnGroup() throws SQLException {
        when(resultSetMock.getLong("group_id")).thenReturn(1L);
        when(resultSetMock.getString("group_name")).thenReturn("TestName");
        assertEquals(new Group(1L, "TestName"), mapperTest.mapRow(resultSetMock, rowNum));
    }
}
