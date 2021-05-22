package by.kopetcev.university.service;

import by.kopetcev.university.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User add(User user);

    boolean deleteUser(Long userId);

    List<User> findAll();

    Optional<User> findById(Long userId);

    Optional<User> findByLoginPassword(String login, String password);

    boolean assignStudent(Long userId, Long groupId);

    boolean assignTeacher(Long userId);

    boolean deleteFromTeacher(Long teacherId);

    boolean deleteFromStudent(Long studentId);

    List<User> findAllTeacher();

    List<User> findAllStudent();
}
