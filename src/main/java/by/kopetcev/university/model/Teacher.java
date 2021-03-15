package by.kopetcev.university.model;

import java.util.Objects;
import java.util.Set;

public class Teacher extends User {
    private Set<Long> courses;

    public Teacher(String login, String password, String email, Set<Long> roles, Set<Long> courses) {
        super(login, password, email, roles);
        this.courses = courses;
    }

    public Teacher(Long id, String login, String password, String email, Set<Long> roles, Set<Long> courses) {
        super(id, login, password, email, roles);
        this.courses = courses;
    }

    public Set<Long> getCourses() {
        return courses;
    }

    public void setCourses(Set<Long> courses) {
        this.courses = courses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Teacher teacher = (Teacher) o;
        return Objects.equals(courses, teacher.courses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), courses);
    }
}
