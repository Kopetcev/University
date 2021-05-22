package by.kopetcev.university.model;

public class Teacher extends User {


    public Teacher(String login, String password, String email, String firstName, String lastName) {
        super(login, password, email, firstName, lastName);
    }

    public Teacher(Long id, String login, String password, String email, String firstName, String lastName) {
        super(id, login, password, email, firstName, lastName);

    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + super.getId() +
                ", login='" + super.getLogin() + '\'' +
                ", password='" + super.getPassword() + '\'' +
                ", email='" + super.getEmail() + '\'' +
                ", firstName='" + super.getFirstName() + '\'' +
                ", lastName='" + super.getLastName() + '\'' +
                '}';
    }
}




