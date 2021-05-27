package by.kopetcev.university.service.impl;

import by.kopetcev.university.model.*;
import by.kopetcev.university.service.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalTime;


import static org.hamcrest.MatcherAssert.assertThat;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {LessonViewServiceImpl.class})
class LessonViewServiceImplTest {

    @MockBean
    private CourseService courseServiceMock;

    @MockBean
    private GroupService groupServiceMock;

    @MockBean
    private UserService userServiceMock;

    @MockBean
    private LessonTimeService lessonTimeServiceMock;

    @MockBean
    private LessonRoomService lessonRoomServiceMock;

    @Autowired
    private LessonViewServiceImpl serviceTest;

    @Test
    void shouldMap() {
        var lesson1 = new Lesson(1L, 1L, 1L, 1L, 1L, 1L, 1L);
        var expected = new LessonView(1L, new Course(1L, "course1"), new Group(1L, "group1"), new Teacher(1L, "login1", "password1", "mail1@mail.com", "name1", "lastName1"), "Monday", new LessonTime(1L, LocalTime.of(1, 0), LocalTime.of(2, 0)), new LessonRoom(1L, "room"));

        when(courseServiceMock.findById(lesson1.getCourseId())).thenReturn(new Course(1L, "course1"));
        when(groupServiceMock.findById(lesson1.getGroupId())).thenReturn(new Group(1L, "group1"));
        when(userServiceMock.findById(lesson1.getTeacherId())).thenReturn(new Teacher(1L, "login1", "password1", "mail1@mail.com", "name1", "lastName1"));
        when(lessonTimeServiceMock.findById(lesson1.getLessonTimeId())).thenReturn(new LessonTime(1L, LocalTime.of(1, 0), LocalTime.of(2, 0)));
        when(lessonRoomServiceMock.findById(lesson1.getLessonRoomId())).thenReturn( new LessonRoom(1L, "room"));

        assertThat(serviceTest.map(lesson1), equalTo(expected));
    }
}
