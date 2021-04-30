package by.kopetcev.university.model;

import java.util.Objects;
import java.util.Set;

public class Student extends User {

    private  Long groupId;

    public Student(String login, String password, String email, String firstName, String lastName, Long groupId) {
        super(login, password, email, firstName, lastName);
        this.groupId = groupId;
    }

    public Student(Long id, String login, String password, String email, String firstName, String lastName, Long groupId ) {
        super(id, login, password, email, firstName, lastName);
        this.groupId = groupId;
    }

    @Override
    public String toString() {
        return "Student{" +
                "groupId=" + groupId +
                '}';
    }
}
