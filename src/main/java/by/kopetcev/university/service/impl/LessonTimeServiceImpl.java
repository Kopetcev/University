package by.kopetcev.university.service.impl;

import by.kopetcev.university.dao.LessonTimeDao;
import by.kopetcev.university.exception.ServiceException;
import by.kopetcev.university.model.LessonTime;
import by.kopetcev.university.service.LessonTimeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
@Transactional
public class LessonTimeServiceImpl implements LessonTimeService {

    private final LessonTimeDao lessonTimeDao;

    private static final Logger logger = LoggerFactory.getLogger(
            LessonTimeServiceImpl.class);

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
       return  lessonTimeDao.findAll();

    }

    @Override
    public LessonTime findById(Long lessonTimeId) {
        Optional<LessonTime> optionalLessonTime = lessonTimeDao.findById(lessonTimeId);
        if (optionalLessonTime.isPresent()) {
            return optionalLessonTime.get();
        } else
            logger.warn("LessonTime with id = {} not found", lessonTimeId);
        throw new ServiceException("LessonTime with id = " + lessonTimeId + " not found");
    }
}
