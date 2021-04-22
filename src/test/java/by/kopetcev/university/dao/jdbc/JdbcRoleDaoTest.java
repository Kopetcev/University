package by.kopetcev.university.dao.jdbc;

import java.util.List;
import java.util.Optional;

import by.kopetcev.university.model.Role;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;



@ContextConfiguration(classes = JdbcRoleDaoTestConfig.class)
@SpringJUnitConfig
@Sql("/sql/database_create.sql")
@Sql("/sql/insert_JdbcRoleDaoTest.sql")
//@Sql("/sql/database_cleanup.sql")
public class JdbcRoleDaoTest {

    @Autowired
    private JdbcRoleDao dao;

    @Test
    void shouldCreate()  {
        Role expected = new Role("created course");
        assertThat(expected.getId(), nullValue());

        Role created = dao.create(expected);
        assertThat(created.getName(), equalTo(expected.getName()));
        assertThat(created.getId(), notNullValue());
        assertThat(created, not(expected));

        Optional<Role> actual = dao.findById(created.getId());
        assertThat(actual.isPresent(), is(true));
        assertThat(created, equalTo(actual.get()));
    }

    @Test
    void shouldFindOne() {
        Optional<Role> found = dao.findById(4L);
        assertThat(found.isPresent(), is(true));
        assertThat(found.get(), equalTo(new Role(4L, "find me")));
    }

    @Test
    void shouldNotFindOneNotExistent() {
        Optional<Role> found = dao.findById(1000L);
        assertThat(found.isPresent(), is(false));
    }

    @Test
    void shouldFindAll() {
        List<Role> all = dao.findAll();
        assertThat(all, hasItems(new Role(1L, "manager"),
                new Role(2L, "intern"),
                new Role(3L, "admin")));
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
        Role upRole = new Role(id, "updated");
        Role updated = dao.update(upRole);
        assertThat(updated, equalTo(upRole));
        assertThat(updated, equalTo(dao.findById(id).get()));
    }

    @Test
    void shouldNotDeleteNotExistent() {
        long id = 12345L;
        assertThat(dao.findById(id).isPresent(), is(false));
        assertThat(dao.deleteById(id), is(false));
    }
}