package by.kopetcev.university.service.impl;

import by.kopetcev.university.dao.UserDao;
import by.kopetcev.university.exception.ServiceException;
import by.kopetcev.university.model.Student;
import by.kopetcev.university.model.Teacher;
import by.kopetcev.university.model.User;
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
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {UserServiceImpl.class})
class UserServiceImplTest {

    @MockBean
    private UserDao userDaoMock;

    @Autowired
    private UserServiceImpl service;

    @Test
    void shouldInvokeSave() {
        User newUser = new User("user", "password8", "user@mail", "Nick", "Bennett");
        User expected = new User(1L, "user", "password8", "user@mail", "Nick", "Bennett");
        when(userDaoMock.save(newUser)).thenReturn(expected);
        assertThat(service.add(newUser), equalTo(expected));
        Mockito.verify(userDaoMock, Mockito.times(1)).save(newUser);
    }

    @Test
    void shouldInvokeFindById() {
        Optional<User> expected = Optional.of(new User(1L, "user", "password8", "user@mail", "Nick", "Bennett"));
        when(userDaoMock.findById(expected.get().getId())).thenReturn(expected);
        assertThat(service.findById(expected.get().getId()), equalTo(expected.get()));
        Mockito.verify(userDaoMock, Mockito.times(1)).findById(expected.get().getId());
    }

    @Test
    void shouldInvokeDeleteById() {
        Long id = 1L;
        when(userDaoMock.deleteById(id)).thenReturn(true);
        assertThat(service.deleteUser(id), is(true));
        Mockito.verify(userDaoMock, Mockito.times(1)).deleteById(id);
    }

    @Test
    void shouldInvokeFindAll() {
        List<User> expected = new ArrayList<>();
        expected.add(new User(1L, "w", "password1", "w3@mail", "Francine", "Parker"));
        expected.add(new User(2L, "e", "password2", "e3@mail", "Kelly", "Hall"));
        when(userDaoMock.findAll()).thenReturn(expected);
        assertThat(service.findAll(), equalTo(expected));
        Mockito.verify(userDaoMock, Mockito.times(1)).findAll();
    }

    @Test
    void shouldInvokeFindByLoginPassword() {
        Optional<User> expected = Optional.of(new User(1L, "find_me", "password3", "r3@mail", "Dulce", "Barnes"));
        when(userDaoMock.findByLoginPassword(expected.get().getLogin(), expected.get().getPassword())).thenReturn(expected);
        assertThat(service.findByLoginPassword(expected.get().getLogin(), expected.get().getPassword()), equalTo(expected));
        Mockito.verify(userDaoMock, Mockito.times(1)).findByLoginPassword(expected.get().getLogin(), expected.get().getPassword());
    }

    @Test
    void shouldInvokeAssignStudent() {
        Long userId = 1L;
        Long groupId = 2L;
        User user = new User(1L, "user", "password8", "user@mail", "Nick", "Bennett");
        when(userDaoMock.findById(userId)).thenReturn(Optional.of(user));
        when(userDaoMock.assignStudent(userId, groupId)).thenReturn(true);
        assertThat(service.assignStudent(userId, groupId), is(true));
        Mockito.verify(userDaoMock, Mockito.times(1)).assignStudent(userId, groupId);
    }

    @Test
    void shouldNotInvokeAssignStudentWhenUserNotExist() {
        Long userId = 1L;
        Long groupId = 2L;
        when(userDaoMock.findById(userId)).thenReturn(Optional.empty());
        assertThrows(ServiceException.class, () -> service.assignStudent(userId, groupId));
        Mockito.verify(userDaoMock, Mockito.never()).assignStudent(userId, groupId);
    }

    @Test
    void shouldNotInvokeAssignStudentWhenUserIsTeacher() {
        Long userId = 1L;
        Long groupId = 2L;
        User user = new Teacher(1L, "user", "password8", "user@mail", "Nick", "Bennett");
        when(userDaoMock.findById(userId)).thenReturn(Optional.of(user));
        assertThrows(ServiceException.class, () -> service.assignStudent(userId, groupId));
        Mockito.verify(userDaoMock, Mockito.never()).assignStudent(userId, groupId);
    }

    @Test
    void shouldInvokeAssignTeacher() {
        Long userId = 1L;
        User user = new User(1L, "user", "password8", "user@mail", "Nick", "Bennett");
        when(userDaoMock.findById(userId)).thenReturn(Optional.of(user));
        when(userDaoMock.assignTeacher(userId)).thenReturn(true);
        assertThat(service.assignTeacher(userId), is(true));
        Mockito.verify(userDaoMock, Mockito.times(1)).assignTeacher(userId);
    }

    @Test
    void shouldNotInvokeAssignTeacherWhenUserNotExist() {
        Long userId = 1L;
        when(userDaoMock.findById(userId)).thenReturn(Optional.empty());
        assertThrows(ServiceException.class, () -> service.assignTeacher(userId));
        Mockito.verify(userDaoMock, Mockito.never()).assignTeacher(userId);
    }

    @Test
    void shouldNotInvokeAssignTeacherWhenUserIsStudent() {
        Long userId = 1L;
        User user = new Student(1L, "user", "password8", "user@mail", "Nick", "Bennett", 1L);
        when(userDaoMock.findById(userId)).thenReturn(Optional.of(user));
        assertThrows(ServiceException.class, () -> service.assignTeacher(userId));
        Mockito.verify(userDaoMock, Mockito.never()).assignTeacher(userId);
    }

    @Test
    void shouldInvokeDeleteFromTeacher() {
        Long id = 1L;
        when(userDaoMock.deleteFromTeacher(id)).thenReturn(true);
        assertThat(service.deleteFromTeacher(id), is(true));
        Mockito.verify(userDaoMock, Mockito.times(1)).deleteFromTeacher(id);
    }

    @Test
    void shouldInvokeDeleteFromStudent() {
        Long id = 1L;
        when(userDaoMock.deleteFromStudent(id)).thenReturn(true);
        assertThat(service.deleteFromStudent(id), is(true));
        Mockito.verify(userDaoMock, Mockito.times(1)).deleteFromStudent(id);
    }


    @Test
    void shouldInvokeFindAllTeacher() {
        List<User> expected = new ArrayList<>();
        expected.add(new Teacher(1L, "w", "password1", "w3@mail", "Francine", "Parker"));
        expected.add(new Teacher(2L, "e", "password2", "e3@mail", "Kelly", "Hall"));
        when(userDaoMock.findAllTeacher()).thenReturn(expected);
        assertThat(service.findAllTeacher(), equalTo(expected));
        Mockito.verify(userDaoMock, Mockito.times(1)).findAllTeacher();
    }

    @Test
    void shouldInvokeFindAllStudent() {
        List<User> expected = new ArrayList<>();
        expected.add(new Student(1L, "w", "password1", "w3@mail", "Francine", "Parker", 2L));
        expected.add(new Student(2L, "e", "password2", "e3@mail", "Kelly", "Hall", 2L));
        when(userDaoMock.findAllStudent()).thenReturn(expected);
        assertThat(service.findAllStudent(), equalTo(expected));
        Mockito.verify(userDaoMock, Mockito.times(1)).findAllStudent();
    }
}
