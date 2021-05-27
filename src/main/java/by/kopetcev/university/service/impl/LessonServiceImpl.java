package by.kopetcev.university.service.impl;

import by.kopetcev.university.dao.LessonDao;
import by.kopetcev.university.exception.ServiceException;
import by.kopetcev.university.model.Lesson;
import by.kopetcev.university.service.LessonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
@Transactional
public class LessonServiceImpl implements LessonService {

    private final LessonDao lessonDao;

    private static final Logger logger = LoggerFactory.getLogger(
            LessonServiceImpl.class);

    @Autowired
    public LessonServiceImpl(LessonDao lessonDao){
        this.lessonDao = lessonDao;
    }

    @Override
    public Lesson add(Lesson lesson) {
        return lessonDao.save(lesson);
    }

    @Override
    public boolean deleteLesson(Long lessonId) {
        return lessonDao.deleteById(lessonId);
    }

    @Override
    public List<Lesson> findAll() {
        return lessonDao.findAll();
    }

    @Override
    public Lesson findById(Long lessonId) {
        Optional<Lesson> optionalLesson = lessonDao.findById(lessonId);
        if (optionalLesson.isPresent()) {
            return optionalLesson.get();
        } else {
            logger.warn("Lesson with id = {} not found", lessonId);
            throw new ServiceException("Lesson with id = " + lessonId + " not found");
        }
    }
}
