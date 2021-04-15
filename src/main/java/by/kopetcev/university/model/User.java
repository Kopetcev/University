package by.kopetcev.university.model;

import java.util.Objects;
import java.util.Set;


public class User implements Entity<Long> {
    private Long id;
    private String login;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private Set<Integer> idRoles;

    public User(String login, String password, String email, String firstName, String lastName) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.firstName =firstName;
        this.lastName = lastName;
    }
    public User(Long id, String login, String password, String email,String firstName, String lastName) {
        this(login, password,email,firstName,lastName);
        this.id = id;
    }


    public User(Long id, String login, String password, String email,String firstName, String lastName , Set<Integer> idRoles) {
        this(login, password,email,firstName,lastName);
        this.id = id;
        this.idRoles=idRoles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long userId) {
        this.id = userId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<Integer> getIdRoles() {
        return idRoles;
    }

    public void setIdRoles(Set<Integer> roles) {
        this.idRoles = idRoles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(login, user.login) &&
                Objects.equals(password, user.password) &&
                Objects.equals(email, user.email) &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(idRoles, user.idRoles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, email, firstName, lastName, idRoles);
    }
}

