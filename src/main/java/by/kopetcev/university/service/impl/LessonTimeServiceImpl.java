package by.kopetcev.university.service.impl;

import by.kopetcev.university.dao.LessonTimeDao;
import by.kopetcev.university.model.LessonTime;
import by.kopetcev.university.service.LessonTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class LessonTimeServiceImpl implements LessonTimeService {

    private final LessonTimeDao lessonTimeDao;

    @Autowired
    public LessonTimeServiceImpl(LessonTimeDao lessonTimeDao){
        this.lessonTimeDao = lessonTimeDao;
    }

    @Override
    public LessonTime add(LessonTime lessonTime) {
        return lessonTimeDao.save(lessonTime);
    }

    @Override
    public boolean deleteLessonTime(Long lessonTimeId) {
        return lessonTimeDao.deleteById(lessonTimeId);
    }

    @Override
    public List<LessonTime> findAll() {
        return lessonTimeDao.findAll();
    }

    @Override
    public Optional<LessonTime> findById(Long lessonTimeId) {
        return lessonTimeDao.findById(lessonTimeId);
    }
}
