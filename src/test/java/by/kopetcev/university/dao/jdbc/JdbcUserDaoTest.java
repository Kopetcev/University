package by.kopetcev.university.dao.jdbc;

import by.kopetcev.university.model.Student;
import by.kopetcev.university.model.Teacher;
import by.kopetcev.university.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;

@Sql(scripts = {"/sql/insert_JdbcUserDaoTest.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = {"/sql/cleanup.sql"}, executionPhase = AFTER_TEST_METHOD)
@SpringBootTest
class JdbcUserDaoTest extends BaseDaoTest {

    @Autowired
    private JdbcUserDao dao;

    @Test
    void shouldCreate() {
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
    void shouldNotFindOneNotExist() {
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

    @Test
    void shouldFindStudent() {
        Optional<User> found = dao.findById(7L);
        assertThat(found.isPresent(), is(true));
        assertThat((Student)found.get(), equalTo(new Student(7L, "student", "password6", "student@mail", "Alex", "Taylor",1L)));
    }

    @Test
    void shouldFindTeacher() {
        Optional<User> found = dao.findById(8L);
        assertThat(found.isPresent(), is(true));
        assertThat((Teacher)found.get(), equalTo(new Teacher(8L, "teacher", "password7", "teach@mail", "Aaron", "Sanders")));
    }

    @Test
    void shouldAssignStudent() {
        User user = new User(3L, "e", "password2", "e3@mail", "Kelly", "Hall");
        Long groupId = 2L;
        Student student = new Student(3L, "e", "password2", "e3@mail", "Kelly", "Hall",groupId);
        assertThat(dao.findAllStudent(), not(hasItems(student)));
        dao.assignStudent(user.getId(), groupId);
        assertThat(dao.findAllStudent(), hasItems(student));
    }

    @Test
    void shouldAssignTeacher() {
        User user = new User(2L,"w", "password1", "w3@mail", "Francine", "Parker");
        Teacher teacher = new Teacher(2L,"w", "password1", "w3@mail", "Francine", "Parker");
               assertThat(dao.findAllTeacher(), not(hasItems(teacher)));
        dao.assignTeacher(user.getId());
        assertThat(dao.findAllTeacher(), hasItems(teacher));
    }

    @Test
    void shouldFindAllStudent() {
        List<User> all = dao.findAllStudent();
        assertThat(all,  containsInAnyOrder(new Student(7L, "student", "password6", "student@mail", "Alex", "Taylor",1L),
                new Student(10L, "student2", "password7", "student2@mail", "Lola", "Garcia",2L)));
    }

    @Test
    void shouldFindAllTeacher() {
        List<User> all = dao.findAllTeacher();
        assertThat(all,  containsInAnyOrder(new Teacher(8L, "teacher", "password7", "teach@mail", "Aaron", "Sanders"),
                new Teacher(11L, "teacher2", "password11", "teach2@mail", "Kelly", "Rodriguez")));
    }

    @Test
    void shouldFindOneByLoginPassword() {
        Optional<User> found = dao.findByLoginPassword("find_me", "password3");
        assertThat(found.isPresent(), is(true));
        assertThat(found.get(), equalTo(new User(4L, "find_me", "password3", "r3@mail", "Dulce", "Barnes")));
    }

    @Test
    void shouldNotFindOneByLoginPasswordNotExistent() {
        Optional<User> found = dao.findByLoginPassword("not exist", "password");
        assertThat(found.isPresent(), is(false));
    }
}
