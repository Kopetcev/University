package by.kopetcev.university.model;

import java.util.Objects;
import java.util.Set;

public class User implements Entity<Long> {
    private Long userId;
    private String login;
    private String password;
    private String email;
    private Set<Long> idRoles;

    public User(String login, String password, String email, Set<Long> idRoles) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.idRoles = idRoles;
    }

    public User(Long id, String login, String password, String email, Set<Long> idRoles) {
        this.userId = id;
        this.login = login;
        this.password = password;
        this.email = email;
        this.idRoles = idRoles;
    }

    public Long getId() {
        return userId;
    }

    public void setId(Long userId) {
        this.userId = userId;
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

    public Set<Long> getIdRoles() {
        return idRoles;
    }

    public void setIdRoles(Set<Long> roles) {
        this.idRoles = idRoles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userId == user.userId &&
                Objects.equals(login, user.login) &&
                Objects.equals(password, user.password) &&
                Objects.equals(email, user.email) &&
                Objects.equals(idRoles, user.idRoles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, login, password, email, idRoles);
    }
}
