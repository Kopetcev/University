package by.kopetcev.university.service.impl;

import by.kopetcev.university.dao.LessonDao;
import by.kopetcev.university.model.Lesson;
import by.kopetcev.university.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class LessonServiceImpl implements LessonService {

    private final LessonDao lessonDao;

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
    public Optional<Lesson> findById(Long lessonId) {
        return lessonDao.findById(lessonId);
    }
}
