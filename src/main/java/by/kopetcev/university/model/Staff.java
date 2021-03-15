package by.kopetcev.university.model;

import java.util.Set;

public class Staff extends User {

    public Staff(String login, String password, String email, Set<Long> roles) {
        super(login, password, email, roles);
    }

    public Staff(Long id, String login, String password, String email, Set<Long> roles) {
        super(id, login, password, email, roles);
    }
}
