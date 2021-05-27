package by.kopetcev.university.model;

import java.util.Objects;

public class User implements Entity<Long> {
    private Long id;
    private String login;
    private String password;
    private String email;
    private String firstName;
    private String lastName;

    public User(){

    }

    public User(String login, String password, String email, String firstName, String lastName) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public User(Long id, String login, String password, String email, String firstName, String lastName) {
        this(login, password, email, firstName, lastName);
        this.id = id;
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        if (null == id) {
            return (id == user.id);
        } else {
            if (!id.equals(user.id)) {
                return false;
            }
        }
        if (null == login) {
            return (login == user.login);
        } else {
            if (!login.equals(user.login)) {
                return false;
            }
        }
        if (null == password) {
            return (password == user.password);
        } else {
            if (!password.equals(user.password)) {
                return false;
            }
        }
        if (null == email) {
            return (email == user.email);
        } else {
            if (!email.equals(user.email)) {
                return false;
            }
        }
        if (null == firstName) {
            return (firstName == user.firstName);
        } else {
            if (!firstName.equals(user.firstName)) {
                return false;
            }
        }
        if (null == lastName) {
            return (lastName == user.lastName);
        } else {
            if (!lastName.equals(user.lastName)) {
                return false;
            }
        }
        return true;

    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, email, firstName, lastName);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
