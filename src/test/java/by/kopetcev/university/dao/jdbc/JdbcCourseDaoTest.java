package by.kopetcev.university.dao.jdbc;

import by.kopetcev.university.model.Course;
import by.kopetcev.university.model.Teacher;
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
@Sql(scripts = {"/sql/insert_JdbcCourseDaoTest.sql"}, executionPhase = BEFORE_TEST_METHOD)
@Sql(scripts = {"/sql/cleanup.sql",}, executionPhase = AFTER_TEST_METHOD)
class JdbcCourseDaoTest extends BaseDaoTest {

    @Autowired
    private JdbcCourseDao dao;

    @Test
    void shouldCreate() {
        Course expected = new Course("created course");
        assertThat(expected.getId(), nullValue());

        Course created = dao.create(expected);
        assertThat(created.getName(), equalTo(expected.getName()));
        assertThat(created.getId(), notNullValue());
        assertThat(created, not(expected));

        Optional<Course> actual = dao.findById(created.getId());
        assertThat(actual.isPresent(), is(true));
        assertThat(created, equalTo(actual.get()));
    }

    @Test
    void shouldFindOne() {
        Optional<Course> found = dao.findById(4L);
        assertThat(found.isPresent(), is(true));
        assertThat(found.get(), equalTo(new Course(4L, "find me")));
    }

    @Test
    void shouldNotFindOneNotExistent() {
        Optional<Course> found = dao.findById(1000L);
        assertThat(found.isPresent(), is(false));
    }

    @Test
    void shouldFindAll() {
        List<Course> all = dao.findAll();
        assertThat(all, hasItems(new Course(1L, "math"),
                new Course(2L, "java"),
                new Course(3L, "literature")));
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
        Course upCourse = new Course(id, "updated");
        Course updated = dao.update(upCourse);
        assertThat(updated, equalTo(upCourse));
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
        Long teacherId = 1L;
        List<Course> courses = dao.findByTeacherId(teacherId);
        assertThat(courses, hasItems(new Course(1L, "math"),
                new Course(2L, "java"),
                new Course(3L, "literature")));
    }


    @Test
    void shouldAssignCourseToTeacher() {
        Course course = new Course(7L, "assign_me");
        Teacher teacher = new Teacher(2L, "q", "password0", "q3@mail", "Giovanna", "Garcia");
        dao.assignTeacher(course.getId(), teacher.getId());
        assertThat(dao.findByTeacherId(teacher.getId()).get(0), equalTo(course));
    }

    @Test
    void shouldDeleteFromTeacher() {
        Long courseId = 8L;
        Long teacherId = 2L;
        assertThat(dao.findByTeacherId(teacherId), hasSize(1));
        assertThat(dao.deleteByIdFromTeacher(courseId, teacherId), is(true));
        assertThat(dao.findByTeacherId(teacherId), hasSize(0));
    }
}
