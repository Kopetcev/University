package by.kopetcev.university.service.impl;

import by.kopetcev.university.dao.LessonDao;
import by.kopetcev.university.model.Lesson;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {LessonServiceImpl.class})
class LessonServiceImplTest {

    @MockBean
    private LessonDao lessonDaoMock;

    @Autowired
    private LessonServiceImpl service;

    @Test
    void shouldInvokeSave() {
        Lesson newLesson = new Lesson(1L, 1L, 1L, LocalDate.of(2021, 9, 1), 1L, 1L);
        Lesson expected = new Lesson(1L,1L, 1L, 1L, LocalDate.of(2021, 9, 1), 1L, 1L);
        when(lessonDaoMock.save(newLesson)).thenReturn(expected);
        assertThat(service.add(newLesson), equalTo(expected));
        Mockito.verify(lessonDaoMock, Mockito.times(1)).save(newLesson);
    }

    @Test
    void shouldInvokeFindById() {
        Optional<Lesson> expected = Optional.of(new Lesson(1L,1L, 1L, 1L, LocalDate.of(2021, 9, 1), 1L, 1L));
        when(lessonDaoMock.findById(expected.get().getId())).thenReturn(expected);
        assertThat(service.findById(expected.get().getId()), equalTo(expected));
        Mockito.verify(lessonDaoMock, Mockito.times(1)).findById(expected.get().getId());
    }

    @Test
    void shouldInvokeDeleteById() {
        Long id = 1L;
        when(lessonDaoMock.deleteById(id)).thenReturn(true);
        assertThat(service.deleteLesson(id), is(true));
        Mockito.verify(lessonDaoMock, Mockito.times(1)).deleteById(id);
    }

    @Test
    void shouldInvokeFindAll() {
        List<Lesson> expected = new ArrayList<>();
        expected.add(new Lesson(1L,1L, 1L, 1L, LocalDate.of(2021, 9, 1), 1L, 1L));
        expected.add(new Lesson(2L,2L, 2L, 2L, LocalDate.of(2021, 9, 2), 2L, 2L));
        when(lessonDaoMock.findAll()).thenReturn(expected);
        assertThat(service.findAll(), equalTo(expected));
        Mockito.verify(lessonDaoMock, Mockito.times(1)).findAll();
    }
}
