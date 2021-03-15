package by.kopetcev.university.model;

import java.util.Objects;
import java.util.Set;

public class Student extends User {

    private  Long groupId;

    public Student(String login, String password, String email, Set<Long> roles, Long groupId) {
        super(login, password, email, roles);
        this.groupId = groupId;
    }

    public Student(Long id, String login, String password, String email, Set<Long> roles) {
        super(id, login, password, email, roles);
        this.groupId = groupId;
    }
}
