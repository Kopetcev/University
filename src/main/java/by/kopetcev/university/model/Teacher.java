package by.kopetcev.university.model;

import java.util.Objects;
import java.util.Set;

public class Teacher extends User {
    private Set<Integer> courses;

    public Teacher(String login, String password, String email, String firstName, String lastName, Set<Integer> courses) {
        super(login, password, email, firstName, lastName);
        this.courses = courses;
    }

    public Teacher(Long id, String login, String password, String email, String firstName, String lastName, Set<Integer> roles, Set<Integer> courses) {
        super(id, login, password, email, firstName, lastName, roles);
        this.courses = courses;
    }

    public Set<Integer> getCourses() {
        return courses;
    }

    public void setCourses(Set<Integer> courses) {
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
