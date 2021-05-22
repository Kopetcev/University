package by.kopetcev.university.dao.jdbc;

import by.kopetcev.university.model.LessonTime;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;

@SpringBootTest
@Sql(scripts = {"/sql/insert_JdbcLessonTimeDaoTest.sql"}, executionPhase = BEFORE_TEST_METHOD)
@Sql(scripts = {"/sql/cleanup.sql"}, executionPhase = AFTER_TEST_METHOD)
class JdbcLessonTimeDaoTest extends BaseDaoTest {

    @Autowired
    private JdbcLessonTimeDao dao;

    @Test
    void shouldCreate() {
        LessonTime expected = new LessonTime(LocalTime.of(15, 10), LocalTime.of(16, 10));
        assertThat(expected.getId(), nullValue());

        LessonTime created = dao.create(expected);
        assertThat(created.getStart(), equalTo(expected.getStart()));
        assertThat(created.getEnd(), equalTo(expected.getEnd()));
        assertThat(created.getId(), notNullValue());
        assertThat(created, not(expected));

        Optional<LessonTime> actual = dao.findById(created.getId());
        assertThat(actual.isPresent(), is(true));
        assertThat(created, equalTo(actual.get()));
    }

    @Test
    void shouldFindOne() {
        Long id = 4L;
        Optional<LessonTime> found = dao.findById(id);
        assertThat(found.isPresent(), is(true));
        assertThat(found.get(), equalTo(new LessonTime(id, LocalTime.of(11, 00), LocalTime.of(12, 00))));
    }

    @Test
    void shouldNotFindOneNotExistent() {
        Optional<LessonTime> found = dao.findById(1000L);
        assertThat(found.isPresent(), is(false));
    }

    @Test
    void shouldFindAll() {
        List<LessonTime> all = dao.findAll();

        assertThat(all, hasItems(new LessonTime(1L, LocalTime.of(8, 00), LocalTime.of(9, 00)),
                new LessonTime(2L, LocalTime.of(9, 00), LocalTime.of(10, 00)),
                new LessonTime(3L, LocalTime.of(10, 00), LocalTime.of(11, 00))));
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
        LessonTime upLessonTime = new LessonTime(id, LocalTime.of(13, 30), LocalTime.of(14, 30));
        LessonTime updated = dao.update(upLessonTime);
        assertThat(updated, equalTo(upLessonTime));
        assertThat(updated, equalTo(dao.findById(id).get()));
    }

    @Test
    void shouldNotDeleteNotExistent() {
        long id = 12345L;
        assertThat(dao.findById(id).isPresent(), is(false));
        assertThat(dao.deleteById(id), is(false));
    }
}
