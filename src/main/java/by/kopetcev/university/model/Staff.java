package by.kopetcev.university.model;

import java.util.Set;

public class Staff extends User {

    public Staff(String login, String password, String email, String firstName, String lastName) {
        super(login, password, email, firstName, lastName);
    }

    public Staff(Long id, String login, String password, String email, String firstName, String lastName, Set<Integer> idRoles) {
        super(id, login, password, email, firstName, lastName, idRoles);
    }
}
