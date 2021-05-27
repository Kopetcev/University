package by.kopetcev.university.service.impl;

import by.kopetcev.university.dao.CourseDao;
import by.kopetcev.university.model.Course;

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


@SpringBootTest(classes = {CourseServiceImpl.class})
class CourseServiceImplTest {

    @MockBean
    private CourseDao courseDaoMock;

    @Autowired
    private CourseServiceImpl service;

    @Test
    void shouldInvokeSave() {
        Course newCourse = new Course("course");
        Course expected = new Course( "course");
        when(courseDaoMock.save(newCourse)).thenReturn(expected);
        assertThat(service.add(newCourse), equalTo(expected));
        Mockito.verify(courseDaoMock, Mockito.times(1)).save(newCourse);
    }

    @Test
    void shouldInvokeFindById() {
        Optional<Course> expected = Optional.of(new Course(1L, "course"));
        when(courseDaoMock.findById(expected.get().getId())).thenReturn(expected);
        assertThat(service.findById(expected.get().getId()), equalTo(expected.get()));
        Mockito.verify(courseDaoMock, Mockito.times(1)).findById(expected.get().getId());
    }

    @Test
    void shouldInvokeDeleteById() {
        Long id = 1L;
        when(courseDaoMock.deleteById(id)).thenReturn(true);
        assertThat(service.deleteCourse(id), is(true));
        Mockito.verify(courseDaoMock, Mockito.times(1)).deleteById(id);
    }

    @Test
    void shouldInvokeFindAll() {
        List<Course> expected = new ArrayList<>();
        expected.add(new Course("math"));
        expected.add(new Course("java"));
        when(courseDaoMock.findAll()).thenReturn(expected);
        assertThat(service.findAll(), equalTo(expected));
        Mockito.verify(courseDaoMock, Mockito.times(1)).findAll();
    }

    @Test
    void shouldInvokeAssignTeacher() {
        Long teacherId = 1L;
        Long courseId = 2L;
        when(courseDaoMock.assignTeacher(teacherId, courseId)).thenReturn(true);
        assertThat(service.assignTeacher(teacherId, courseId), is(true));
        Mockito.verify(courseDaoMock, Mockito.times(1)).assignTeacher(teacherId, courseId);
    }

    @Test
    void shouldInvokeDeleteFromTeacher() {
        Long teacherId = 1L;
        Long courseId = 2L;
        when(courseDaoMock.deleteByIdFromTeacher(courseId,teacherId)).thenReturn(true);
        assertThat(service.deleteByIdFromTeacher(courseId,teacherId), is(true));
        Mockito.verify(courseDaoMock, Mockito.times(1)).deleteByIdFromTeacher(courseId,teacherId);
    }

    @Test
    void shouldInvokeFindAllCourseByTeacher() {
        Long teacherId = 1L;
        List<Course> expected = new ArrayList<>();
        expected.add(new Course(1L, "math" ));
        expected.add(new Course(2L, "java"));
        when(courseDaoMock.findByTeacherId(teacherId)).thenReturn(expected);
        assertThat(service.findByTeacherId(teacherId), equalTo(expected));
        Mockito.verify(courseDaoMock, Mockito.times(1)).findByTeacherId(teacherId);
    }
}
