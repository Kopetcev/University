package by.kopetcev.university.dao.jdbc;

import java.util.List;
import java.util.Optional;

import by.kopetcev.university.model.User;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@ContextConfiguration(classes = JdbcUserDaoTestConfig.class)
@SpringJUnitConfig
@Sql({"/sql/database_create.sql", "/sql/insert_JdbcUserDaoTest.sql"})
class JdbcUserDaoTest {

    @Autowired
    private JdbcUserDao dao;

    @Test
    void shouldCreate()  {
        User expected = new User("created", "password", "creat@mail.com", "Tom", "Garcia");
        assertThat(expected.getId(), nullValue());

        User created = dao.create(expected);
        assertThat(created.getLogin(), equalTo(expected.getLogin()));
        assertThat(created.getPassword(), equalTo(expected.getPassword()));
        assertThat(created.getEmail(), equalTo(expected.getEmail()));
        assertThat(created.getFirstName(), equalTo(expected.getFirstName()));
        assertThat(created.getLastName(), equalTo(expected.getLastName()));

        assertThat(created.getId(), notNullValue());
        assertThat(created, not(expected));

        Optional<User> actual = dao.findById(created.getId());
        assertThat(actual.isPresent(), is(true));
        assertThat(created, equalTo(actual.get()));
    }

    @Test
    void shouldFindOne() {
        Optional<User> found = dao.findById(4L);
        assertThat(found.isPresent(), is(true));
        assertThat(found.get(), equalTo(new User(4L, "find_me", "password3", "r3@mail", "Dulce", "Barnes")));
    }

    @Test
    void shouldNotFindOneNotExistent() {
        Optional<User> found = dao.findById(1000L);
        assertThat(found.isPresent(), is(false));
    }

    @Test
    void shouldFindAll() {
        List<User> all = dao.findAll();
        assertThat(all, hasItems(new User(1L, "q", "password0", "q3@mail", "Giovanna", "Garcia"),
                new User(2L, "w", "password1", "w3@mail", "Francine", "Parker"),
                new User(3L, "e", "password2", "e3@mail", "Kelly", "Hall")));
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
        User upUser = new User(6L, "updated", "password5", "y@mail", "Valencia", "Simmons");
        User updated = dao.update(upUser);
        assertThat(updated, equalTo(upUser));
        assertThat(updated, equalTo(dao.findById(id).get()));
    }

    @Test
    void shouldNotDeleteNotExistent() {
        long id = 12345L;
        assertThat(dao.findById(id).isPresent(), is(false));
        assertThat(dao.deleteById(id), is(false));
    }
}