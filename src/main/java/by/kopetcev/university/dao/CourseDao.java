package by.kopetcev.university.dao;

import by.kopetcev.university.model.Course;
import by.kopetcev.university.model.Teacher;

import java.util.List;

public interface CourseDao extends CrudDao<Course, Long> {

    boolean assignTeacher(Course course, Teacher teacher);

    List<Course> findByTeacherId(Long TeacherId);

    boolean  deleteByIdFromTeacher(Long courseId, Long teacherId);
}