package by.kopetcev.university.dao;

import by.kopetcev.university.model.User;

import java.util.List;
import java.util.Optional;

public interface UserDao extends CrudDao<User, Long> {

    Optional<User> findByLoginPassword(String login, String password);

    boolean assignStudent(Long userId, Long groupId);

    boolean assignTeacher(Long userId);

    boolean deleteFromTeacher(Long teacherId);

    boolean deleteFromStudent(Long studentId);

    List<User> findAllTeacher();

    List<User> findAllStudent();
}
