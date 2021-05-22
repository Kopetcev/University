package by.kopetcev.university.dao.jdbc;

import by.kopetcev.university.model.Group;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;

@SpringBootTest
@Sql(scripts = {"/sql/insert_JdbcGroupDaoTest.sql"}, executionPhase = BEFORE_TEST_METHOD)
@Sql(scripts = {"/sql/cleanup.sql",}, executionPhase = AFTER_TEST_METHOD)
class JdbcGroupDaoTest extends BaseDaoTest {

    @Autowired
    private JdbcGroupDao dao;

    @Test
    void shouldCreate() {
        Group expected = new Group("created group");
        assertThat(expected.getId(), nullValue());

        Group created = dao.create(expected);
        assertThat(created.getName(), equalTo(expected.getName()));
        assertThat(created.getId(), notNullValue());
        assertThat(created, not(expected));

        Optional<Group> actual = dao.findById(created.getId());
        assertThat(actual.isPresent(), is(true));
        assertThat(created, equalTo(actual.get()));
    }

    @Test
    void shouldFindOne() {
        Optional<Group> found = dao.findById(4L);
        assertThat(found.isPresent(), is(true));
        assertThat(found.get(), equalTo(new Group(4L, "find me")));
    }

    @Test
    void shouldNotFindOneNotExistent() {
        Optional<Group> found = dao.findById(1000L);
        assertThat(found.isPresent(), is(false));
    }

    @Test
    void shouldFindAll() {
        List<Group> all = dao.findAll();
        assertThat(all, hasItems(new Group(1L, "1-G"),
                new Group(2L, "2-G"),
                new Group(3L, "3-G")));
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
        Group upGroup = new Group(id, "updated");
        Group updated = dao.update(upGroup);
        assertThat(updated, equalTo(upGroup));
        assertThat(updated, equalTo(dao.findById(id).get()));
    }

    @Test
    void shouldNotDeleteNotExistent() {
        long id = 12345L;
        assertThat(dao.findById(id).isPresent(), is(false));
        assertThat(dao.deleteById(id), is(false));
    }
}
