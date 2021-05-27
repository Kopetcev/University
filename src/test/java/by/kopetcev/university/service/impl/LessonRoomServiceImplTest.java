package by.kopetcev.university.service.impl;

import by.kopetcev.university.dao.LessonRoomDao;
import by.kopetcev.university.model.LessonRoom;
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

@SpringBootTest(classes = {LessonRoomServiceImpl.class})
class LessonRoomServiceImplTest {

    @MockBean
    private LessonRoomDao lessonRoomDaoMock;

    @Autowired
    private LessonRoomServiceImpl service;

    @Test
    void shouldInvokeSave() {
        LessonRoom newLessonRoom = new LessonRoom("101");
        LessonRoom expected = new LessonRoom( "101");
        when(lessonRoomDaoMock.save(newLessonRoom)).thenReturn(expected);
        assertThat(service.add(newLessonRoom), equalTo(expected));
        Mockito.verify(lessonRoomDaoMock, Mockito.times(1)).save(newLessonRoom);
    }

    @Test
    void shouldInvokeFindById() {
        Optional<LessonRoom> expected = Optional.of(new LessonRoom(1L, "101"));
        when(lessonRoomDaoMock.findById(expected.get().getId())).thenReturn(expected);
        assertThat(service.findById(expected.get().getId()), equalTo(expected.get()));
        Mockito.verify(lessonRoomDaoMock, Mockito.times(1)).findById(expected.get().getId());
    }

    @Test
    void shouldInvokeDeleteById() {
        Long id = 1L;
        when(lessonRoomDaoMock.deleteById(id)).thenReturn(true);
        assertThat(service.deleteLessonRoom(id), is(true));
        Mockito.verify(lessonRoomDaoMock, Mockito.times(1)).deleteById(id);
    }

    @Test
    void shouldInvokeFindAll() {
        List<LessonRoom> expected = new ArrayList<>();
        expected.add(new LessonRoom("101"));
        expected.add(new LessonRoom("102"));
        when(lessonRoomDaoMock.findAll()).thenReturn(expected);
        assertThat(service.findAll(), equalTo(expected));
        Mockito.verify(lessonRoomDaoMock, Mockito.times(1)).findAll();
    }
}
