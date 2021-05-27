package by.kopetcev.university.service.impl;

import by.kopetcev.university.dao.LessonTimeDao;
import by.kopetcev.university.model.LessonTime;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {LessonTimeServiceImpl.class})
class LessonTimeServiceImplTest {

    @MockBean
    private LessonTimeDao lessonTimeDaoMock;

    @Autowired
    private LessonTimeServiceImpl service;

    @Test
    void shouldInvokeSave() {
        LessonTime newLessonRoom = new LessonTime(LocalTime.of(8, 0), LocalTime.of(9, 0));
        LessonTime expected = new LessonTime(1L, LocalTime.of(8, 0), LocalTime.of(9, 0));
        when(lessonTimeDaoMock.save(newLessonRoom)).thenReturn(expected);
        assertThat(service.add(newLessonRoom), equalTo(expected));
        Mockito.verify(lessonTimeDaoMock, Mockito.times(1)).save(newLessonRoom);
    }

    @Test
    void shouldInvokeFindById() {
        Optional<LessonTime> expected = Optional.of(new LessonTime(1L, LocalTime.of(10, 0), LocalTime.of(11, 0)));
        when(lessonTimeDaoMock.findById(expected.get().getId())).thenReturn(expected);
        assertThat(service.findById(expected.get().getId()), equalTo(expected.get()));
        Mockito.verify(lessonTimeDaoMock, Mockito.times(1)).findById(expected.get().getId());
    }

    @Test
    void shouldInvokeDeleteById() {
        Long id = 1L;
        when(lessonTimeDaoMock.deleteById(id)).thenReturn(true);
        assertThat(service.deleteLessonTime(id), is(true));
        Mockito.verify(lessonTimeDaoMock, Mockito.times(1)).deleteById(id);
    }

    @Test
    void shouldInvokeFindAll() {
        List<LessonTime> expected = new ArrayList<>();
        expected.add(new LessonTime(1L, LocalTime.of(8, 0), LocalTime.of(9, 0)));
        expected.add(new LessonTime(2L, LocalTime.of(9, 0), LocalTime.of(10, 0)));
        when(lessonTimeDaoMock.findAll()).thenReturn(expected);
        assertThat(service.findAll(), equalTo(expected));
        Mockito.verify(lessonTimeDaoMock, Mockito.times(1)).findAll();
    }
}
