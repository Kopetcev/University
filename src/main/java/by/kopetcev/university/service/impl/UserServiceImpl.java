package by.kopetcev.university.service.impl;

import by.kopetcev.university.dao.UserDao;

import by.kopetcev.university.exception.ServiceException;
import by.kopetcev.university.model.Student;
import by.kopetcev.university.model.Teacher;
import by.kopetcev.university.model.User;
import by.kopetcev.university.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao){
        this.userDao = userDao;
    }

    @Override
    public User add(User user) {
        return userDao.save(user);
    }

    @Override
    public boolean deleteUser(Long userId) {
        return userDao.deleteById(userId);
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public Optional<User> findById(Long userId) {
        return userDao.findById(userId);
    }

    @Override
    public Optional<User> findByLoginPassword(String login, String password) {
        return userDao.findByLoginPassword(login, password);
    }

    @Override
    public boolean assignStudent(Long userId, Long groupId) {
        Optional<User> optionalUser = userDao.findById(userId);
        if(optionalUser.isEmpty()){
            throw new ServiceException("User does not exist with id =" + userId);
        }
        if(optionalUser.get() instanceof Teacher){
            throw new ServiceException("User with id = " + userId + " is already a teacher =");
        }

        return userDao.assignStudent(userId, groupId);

    }

    @Override
    public boolean assignTeacher(Long userId) {
        Optional<User> optionalUser = userDao.findById(userId);
        if(optionalUser.isEmpty()){
            throw new ServiceException("User does not exist with id =" + userId);
        }
        if(optionalUser.get() instanceof Student){
            throw new ServiceException("User with id = " + userId + " is already a student =");
        }
        return userDao.assignTeacher(userId);
    }

    @Override
    public boolean deleteFromTeacher(Long teacherId) {
        return userDao.deleteFromTeacher(teacherId);
    }

    @Override
    public boolean deleteFromStudent(Long studentId) {
        return userDao.deleteFromStudent(studentId);
    }

    @Override
    public List<User> findAllTeacher() {
        return userDao.findAllTeacher();
    }

    @Override
    public List<User> findAllStudent() {
        return userDao.findAllStudent();
    }
}
