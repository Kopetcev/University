package by.kopetcev.university.dao.jdbc;

import by.kopetcev.university.model.Lesson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;

@SpringBootTest
@Sql(scripts = {"/sql/insert_JdbcLessonDaoTest.sql"}, executionPhase = BEFORE_TEST_METHOD)
@Sql(scripts = {"/sql/cleanup.sql"}, executionPhase = AFTER_TEST_METHOD)
class JdbcLessonDaoTest extends BaseDaoTest {

    @Autowired
    private JdbcLessonDao dao;

    @Test
    void shouldCreate() {
        Lesson expected = new Lesson(3L, 2L, 3L, LocalDate.of(2021, 9, 9), 2L, 3L);
        assertThat(expected.getId(), nullValue());

        Lesson created = dao.create(expected);
        assertThat(created.getCourseId(), equalTo(expected.getCourseId()));
        assertThat(created.getGroupId(), equalTo(expected.getGroupId()));
        assertThat(created.getDate(), equalTo(expected.getDate()));
        assertThat(created.getLessonTimeId(), equalTo(expected.getLessonTimeId()));
        assertThat(created.getLessonRoomId(), equalTo(expected.getLessonRoomId()));
        assertThat(created.getId(), notNullValue());
        assertThat(created, not(expected));

        Optional<Lesson> actual = dao.findById(created.getId());
        assertThat(actual.isPresent(), is(true));
        assertThat(actual.get(), equalTo(created));
    }

    @Test
    void shouldFindOne() {
        Optional<Lesson> found = dao.findById(4L);
        assertThat(found.isPresent(), is(true));
        assertThat(found.get(), equalTo(new Lesson(4L, 1L, 2L, 3L, LocalDate.of(2021, 10, 1), 1L, 2L)));
    }

    @Test
    void shouldNotFindOneNotExistent() {
        Optional<Lesson> found = dao.findById(1000L);
        assertThat(found.isPresent(), is(false));
    }

    @Test
    void shouldFindAll() {
        List<Lesson> all = dao.findAll();
        assertThat(all, hasItems(new Lesson(1L, 1L, 1L, 1L, LocalDate.of(2021, 9, 1), 1L, 1L),
                new Lesson(2L, 2L, 2L, 2L, LocalDate.of(2021, 9, 2), 2L, 2L),
                new Lesson(3L, 3L, 3L, 3L, LocalDate.of(2021, 9, 3), 3L, 3L)));
    }

    @Test
    void shouldDelete() {
        long id = 5L;
        assertThat(dao.findById(id).isPresent(), is(true));
        assertThat(dao.deleteById(id), is(true));
        assertThat(dao.findById(id).isPresent(), is(false));
    }

    @Test
    void shouldUpdate() {
        long id = 6L;
        Lesson upLesson = new Lesson(id, 3L, 3L, 3L, LocalDate.of(2021, 9, 6), 3L, 3L);
        Lesson updated = dao.update(upLesson);
        assertThat(updated, equalTo(upLesson));
        assertThat(updated, equalTo(dao.findById(id).get()));
    }

    @Test
    void shouldNotDeleteNotExistent() {
        long id = 12345L;
        assertThat(dao.findById(id).isPresent(), is(false));
        assertThat(dao.deleteById(id), is(false));
    }
}
