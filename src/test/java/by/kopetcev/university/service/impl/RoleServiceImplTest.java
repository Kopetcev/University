package by.kopetcev.university.service.impl;

import by.kopetcev.university.dao.RoleDao;
import by.kopetcev.university.model.Role;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {RoleServiceImpl.class})
class RoleServiceImplTest {

    @MockBean
    private RoleDao roleDaoMock;

    @Autowired
    private RoleServiceImpl service;

    @Test
    void shouldInvokeSave() {
        Role newRole = new Role("admin");
        Role expected = new Role("admin");
        when(roleDaoMock.save(newRole)).thenReturn(expected);
        assertThat(service.add(newRole), equalTo(expected));
        Mockito.verify(roleDaoMock, Mockito.times(1)).save(newRole);
    }

    @Test
    void shouldInvokeFindById() {
        Optional<Role> expected = Optional.of(new Role(1L, "manager"));
        when(roleDaoMock.findById(expected.get().getId())).thenReturn(expected);
        assertThat(service.findById(expected.get().getId()), equalTo(expected.get()));
        Mockito.verify(roleDaoMock, Mockito.times(1)).findById(expected.get().getId());
    }

    @Test
    void shouldInvokeDeleteById() {
        Long id = 1L;
        when(roleDaoMock.deleteById(id)).thenReturn(true);
        assertThat(service.deleteRole(id), is(true));
        Mockito.verify(roleDaoMock, Mockito.times(1)).deleteById(id);
    }

    @Test
    void shouldInvokeFindAll() {
        List<Role> expected = new ArrayList<>();
        expected.add(new Role("admin"));
        expected.add(new Role("manager"));
        when(roleDaoMock.findAll()).thenReturn(expected);
        assertThat(service.findAll(), equalTo(expected));
        Mockito.verify(roleDaoMock, Mockito.times(1)).findAll();
    }

    @Test
    void shouldInvokeAssignUser() {
        Long userId = 1L;
        Long roleId = 2L;
        when(roleDaoMock.assignUser(roleId, userId)).thenReturn(true);
        assertThat(service.assignUser(roleId, userId), is(true));
        Mockito.verify(roleDaoMock, Mockito.times(1)).assignUser(roleId, userId);
    }

    @Test
    void shouldInvokeDeleteFromUser() {
        Long teacherId = 1L;
        Long courseId = 2L;
        when(roleDaoMock.deleteByIdFromUser(courseId, teacherId)).thenReturn(true);
        assertThat(service.deleteByIdFromUser(courseId, teacherId), is(true));
        Mockito.verify(roleDaoMock, Mockito.times(1)).deleteByIdFromUser(courseId, teacherId);
    }

    @Test
    void shouldInvokeFindAllCourseByTeacher() {
        Long teacherId = 1L;
        List<Role> expected = new ArrayList<>();
        expected.add(new Role(1L, "intern"));
        expected.add(new Role(2L, "staff"));
        when(roleDaoMock.findByUserId(teacherId)).thenReturn(expected);
        assertThat(service.findByUserId(teacherId), equalTo(expected));
        Mockito.verify(roleDaoMock, Mockito.times(1)).findByUserId(teacherId);
    }
}
