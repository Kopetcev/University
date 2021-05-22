package by.kopetcev.university.dao;

import by.kopetcev.university.model.Course;
import by.kopetcev.university.model.Teacher;

import java.util.List;

public interface CourseDao extends CrudDao<Course, Long> {

    boolean assignTeacher(Long CourseId, Long teacherId);

    List<Course> findByTeacherId(Long teacherId);

    boolean deleteByIdFromTeacher(Long courseId, Long teacherId);
}
