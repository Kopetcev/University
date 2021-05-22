package by.kopetcev.university.service;

import by.kopetcev.university.model.Course;

import java.util.List;
import java.util.Optional;


public interface CourseService {

    Course add(Course course);

    boolean deleteCourse(Long courseId);

    List<Course> findAll();

    Optional<Course> findById(Long courseId);

    boolean assignTeacher(Long courseId, Long teacherId);

    List<Course> findByTeacherId(Long teacherId);

    boolean deleteByIdFromTeacher(Long courseId, Long teacherId);
}





