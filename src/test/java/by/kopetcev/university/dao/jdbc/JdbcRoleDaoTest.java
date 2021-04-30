package by.kopetcev.university.dao.jdbc;

import java.util.List;
import java.util.Optional;

import by.kopetcev.university.model.Course;
import by.kopetcev.university.model.Role;
import by.kopetcev.university.model.Teacher;
import by.kopetcev.university.model.User;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;



@ContextConfiguration(classes = JdbcRoleDaoTestConfig.class)
@SpringJUnitConfig
@Sql({"/sql/database_create.sql", "/sql/insert_JdbcRoleDaoTest.sql"})
public class JdbcRoleDaoTest {

    @Autowired
    private JdbcRoleDao dao;

    @Test
    void shouldCreate()  {
        Role expected = new Role("created role");
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

    @Test
    void shouldFindByTeacherId() {
        Long userId = 1L;
        List<Role> roles = dao.findByUserId(userId);
        assertThat(roles, hasItems(new Role(1L, "manager"),
                new Role(2L, "intern"),
                new Role(3L, "admin")));
    }


    @Test
    void shouldAssignCourseToTeacher() {
        Role role = new Role(7L, "assign_me");
        User user = new User(2L, "q", "password0", "q3@mail", "Giovanna", "Garcia");
        dao.assignUser(role, user);
        assertThat(dao.findByUserId(user.getId()).get(0), equalTo(role));
    }

    @Test
    void shouldDeleteFromTeacher() {
        Long roleId = 8L;
        Long userId = 3L;
        assertThat(dao.findByUserId(userId), hasSize(1));
        assertThat(dao.deleteByIdFromUser(roleId,userId), is(true));
        assertThat(dao.findByUserId(userId), hasSize(0));
    }
}