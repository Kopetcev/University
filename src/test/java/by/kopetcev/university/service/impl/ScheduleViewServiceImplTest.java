package by.kopetcev.university.service.impl;

import by.kopetcev.university.model.*;
import by.kopetcev.university.service.LessonViewService;
import by.kopetcev.university.service.ScheduleViewService;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;


@SpringBootTest(classes = {ScheduleViewServiceImpl.class})
class ScheduleViewServiceImplTest {

    @Autowired
    private ScheduleViewService serviceTest;

    @MockBean
    private LessonViewService lessonViewServiceMock;

    @Test
    void shouldMap() {
        var lesson1 = new Lesson(1L,1L,1L,1L,1L,1L,1L);
        var lesson2 = new Lesson(2L,2L,2L,2L,1L,1L,2L);
        var lesson3 = new Lesson(3L,3L,3L,3L,1L,2L,3L);
        var lesson4 = new Lesson(4L,4L,4L,4L,1L,2L,4L);
        var lesson5 = new Lesson(5L,5L,5L,5L,1L,1L,5L);
        var lesson6 = new Lesson(6L,6L,6L,6L,2L,1L,6L);
        var lesson7 = new Lesson(7L,7L,7L,7L,2L,3L,7L);
        var lesson8 = new Lesson(8L,8L,8L,8L,2L,3L,8L);
        List<Lesson> lessons = new ArrayList<>();
        lessons.add(lesson1);
        lessons.add(lesson2);
        lessons.add(lesson3);
        lessons.add(lesson4);
        lessons.add(lesson5);
        lessons.add(lesson6);
        lessons.add(lesson7);
        lessons.add(lesson8);
        var lessonView1 = new LessonView(1L, new Course(1L,"course1"), new Group(1L, "group1"), new Teacher(1L,"login1", "password1","mail1@mail.com","name1", "lastName1"),"Monday",new LessonTime(1L, LocalTime.of(1,0), LocalTime.of(2,0)), new LessonRoom(1L, "room"));
        var lessonView2 = new LessonView(2L, new Course(2L,"course2"), new Group(1L, "group2"), new Teacher(2L,"login2", "password2","mail2@mail.com","name2", "lastName2"),"Monday",new LessonTime(1L, LocalTime.of(1,0), LocalTime.of(2,0)), new LessonRoom(1L, "room"));
        var lessonView3 = new LessonView(3L, new Course(3L,"course3"), new Group(1L, "group3"), new Teacher(3L,"login3", "password3","mail3@mail.com","name3", "lastName3"),"Monday",new LessonTime(2L, LocalTime.of(2,0), LocalTime.of(2,0)), new LessonRoom(1L, "room"));
        var lessonView4 = new LessonView(4L, new Course(4L,"course4"), new Group(1L, "group4"), new Teacher(4L,"login4", "password4","mail4@mail.com","name4", "lastName4"),"Monday",new LessonTime(2L, LocalTime.of(2,0), LocalTime.of(3,0)), new LessonRoom(1L, "room"));
        var lessonView5 = new LessonView(5L, new Course(5L,"course5"), new Group(1L, "group5"), new Teacher(5L,"login5", "password5","mail5@mail.com","name5", "lastName5"),"Monday",new LessonTime(1L, LocalTime.of(1,0), LocalTime.of(2,0)), new LessonRoom(1L, "room"));
        var lessonView6 = new LessonView(6L, new Course(6L,"course6"), new Group(1L, "group6"), new Teacher(6L,"login6", "password6","mail6@mail.com","name6", "lastName6"),"Tuesday",new LessonTime(1L, LocalTime.of(1,0), LocalTime.of(2,0)), new LessonRoom(1L, "room"));
        var lessonView7 = new LessonView(7L, new Course(7L,"course7"), new Group(1L, "group7"), new Teacher(7L,"login7", "password7","mail7@mail.com","name7", "lastName7"),"Tuesday",new LessonTime(3L, LocalTime.of(3,0), LocalTime.of(4,0)), new LessonRoom(1L, "room"));
        var lessonView8 = new LessonView(8L, new Course(8L,"course8"), new Group(1L, "group8"), new Teacher(8L,"login8", "password8","mail8@mail.com","name8", "lastName8"),"Tuesday",new LessonTime(3L, LocalTime.of(3,0), LocalTime.of(4,0)), new LessonRoom(1L, "room"));
        when(lessonViewServiceMock.map(lesson1)).thenReturn(lessonView1);
        when(lessonViewServiceMock.map(lesson2)).thenReturn(lessonView2);
        when(lessonViewServiceMock.map(lesson3)).thenReturn(lessonView3);
        when(lessonViewServiceMock.map(lesson4)).thenReturn(lessonView4);
        when(lessonViewServiceMock.map(lesson5)).thenReturn(lessonView5);
        when(lessonViewServiceMock.map(lesson6)).thenReturn(lessonView6);
        when(lessonViewServiceMock.map(lesson7)).thenReturn(lessonView7);
        when(lessonViewServiceMock.map(lesson8)).thenReturn(lessonView8);

        ScheduleView actual = serviceTest.map(lessons);

        assertThat(actual.getLessons().get("Monday").get(1L), contains(lessonView1, lessonView2, lessonView5 ));
        assertThat(actual.getLessons().get("Monday").get(2L), contains(lessonView3, lessonView4));
        assertThat(actual.getLessons().get("Tuesday").get(1L), contains(lessonView6));
        assertThat(actual.getLessons().get("Tuesday").get(3L), contains(lessonView7,lessonView8));
    }
}
