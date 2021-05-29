package by.kopetcev.university.model;

import java.util.Objects;

public class Student extends User {

    private Long groupId;

    public Student(String login, String password, String email, String firstName, String lastName, Long groupId) {
        super(login, password, email, firstName, lastName);
        this.groupId = groupId;
    }

    public Student(Long id, String login, String password, String email, String firstName, String lastName, Long groupId) {
        super(id, login, password, email, firstName, lastName);
        this.groupId = groupId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        var student = (Student) o;
        return Objects.equals(groupId, student.groupId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), groupId);
    }

    @Override
    public String toString() {
        return "Student{{" +
                "id=" + super.getId() +
                ", login='" + super.getLogin() + '\'' +
                ", password='" + super.getPassword() + '\'' +
                ", email='" + super.getEmail() + '\'' +
                ", firstName='" + super.getFirstName() + '\'' +
                ", lastName='" + super.getLastName() + '\'' +
                ", groupId='" + groupId + '\'' +
                '}';
    }
}
