package by.kopetcev.university.model;

public class Teacher extends User {


    public Teacher(String login, String password, String email, String firstName, String lastName) {
        super(login, password, email, firstName, lastName);
    }

    public Teacher(Long id, String login, String password, String email, String firstName, String lastName) {
        super(id, login, password, email, firstName, lastName);

    }
}




