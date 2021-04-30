package by.kopetcev.university.dao.jdbc;

import java.util.List;
import java.util.Optional;

import by.kopetcev.university.model.LessonRoom;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@ContextConfiguration(classes = JdbcLessonRoomDaoTestConfig.class)
@SpringJUnitConfig
@Sql({"/sql/database_create.sql", "/sql/insert_JdbcLessonRoomDaoTest.sql"})
class JdbcLessonRoomDaoTest {

    @Autowired
    private JdbcLessonRoomDao dao;

    @Test
    void shouldCreate()  {
        LessonRoom expected = new LessonRoom("created lesson room");
        assertThat(expected.getId(), nullValue());

        LessonRoom created = dao.create(expected);
        assertThat(created.getName(), equalTo(expected.getName()));
        assertThat(created.getId(), notNullValue());
        assertThat(created, not(expected));

        Optional<LessonRoom> actual = dao.findById(created.getId());
        assertThat(actual.isPresent(), is(true));
        assertThat(created, equalTo(actual.get()));
    }

    @Test
    void shouldFindOne() {
        Optional<LessonRoom> found = dao.findById(4L);
        assertThat(found.isPresent(), is(true));
        assertThat(found.get(), equalTo(new LessonRoom(4L, "find_me")));
    }

    @Test
    void shouldNotFindOneNotExistent() {
        Optional<LessonRoom> found = dao.findById(1000L);
        assertThat(found.isPresent(), is(false));
    }

    @Test
    void shouldFindAll() {
        List<LessonRoom> all = dao.findAll();
        assertThat(all, hasItems(new LessonRoom(1L, "101"),
                new LessonRoom(2L, "102"),
                new LessonRoom(3L, "103")));
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
        LessonRoom upLessonRoom = new LessonRoom(id, "updated");
        LessonRoom updated = dao.update(upLessonRoom);
        assertThat(updated, equalTo(upLessonRoom));
        assertThat(updated, equalTo(dao.findById(id).get()));
    }

    @Test
    void shouldNotDeleteNotExistent() {
        long id = 12345L;
        assertThat(dao.findById(id).isPresent(), is(false));
        assertThat(dao.deleteById(id), is(false));
    }
}