package by.kopetcev.university.service.impl;

import by.kopetcev.university.dao.CourseDao;
import by.kopetcev.university.exception.ServiceException;
import by.kopetcev.university.model.Course;
import by.kopetcev.university.service.CourseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
@Transactional
public class CourseServiceImpl implements CourseService {

    private final CourseDao courseDao;

    private static final Logger logger = LoggerFactory.getLogger(
            CourseServiceImpl.class);

    @Autowired
    public CourseServiceImpl(CourseDao courseDao) {
        this.courseDao = courseDao;
    }

    public Course add(Course course) {
        return courseDao.save(course);
    }

    public boolean deleteCourse(Long courseId) {
        return courseDao.deleteById(courseId);
    }

    public List<Course> findAll() {
        return courseDao.findAll();
    }

    @Override
    public Course findById(Long courseId) {
        Optional<Course> optionalCourse = courseDao.findById(courseId);
        if (optionalCourse.isPresent()) {
            return optionalCourse.get();
        } else {
            logger.warn("Course with id = {} not found", courseId);
            throw new ServiceException("Course with id = " + courseId + " not found");
        }
    }

    @Override
    public boolean assignTeacher(Long courseId, Long teacherId) {
        return courseDao.assignTeacher(courseId, teacherId);
    }

    @Override
    public List<Course> findByTeacherId(Long teacherId) {
        return courseDao.findByTeacherId(teacherId);
    }


    @Override
    public boolean deleteByIdFromTeacher(Long courseId, Long teacherId) {
        return courseDao.deleteByIdFromTeacher(courseId, teacherId);
    }
}
