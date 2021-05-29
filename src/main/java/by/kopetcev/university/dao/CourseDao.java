package by.kopetcev.university.dao;

import by.kopetcev.university.model.Course;

import java.util.List;

public interface CourseDao extends CrudDao<Course, Long> {

    boolean assignTeacher(Long courseId, Long teacherId);

    List<Course> findByTeacherId(Long teacherId);

    boolean deleteByIdFromTeacher(Long courseId, Long teacherId);
}
