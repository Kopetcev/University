package by.kopetcev.university.service.impl;

import by.kopetcev.university.dao.LessonRoomDao;
import by.kopetcev.university.exception.ServiceException;
import by.kopetcev.university.model.LessonRoom;
import by.kopetcev.university.service.LessonRoomService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
@Transactional
public class LessonRoomServiceImpl implements LessonRoomService {

    private final LessonRoomDao lessonRoomDao;

    private static final Logger logger = LoggerFactory.getLogger(
            LessonRoomServiceImpl.class);

    @Autowired
    public LessonRoomServiceImpl(LessonRoomDao lessonRoomDao) {
        this.lessonRoomDao = lessonRoomDao;
    }

    @Override
    public LessonRoom add(LessonRoom lessonRoom) {
        return lessonRoomDao.save(lessonRoom);
    }

    @Override
    public boolean deleteLessonRoom(Long lessonRoomId) {
        return lessonRoomDao.deleteById(lessonRoomId);
    }

    @Override
    public List<LessonRoom> findAll() {
       return lessonRoomDao.findAll();
    }

    @Override
    public LessonRoom findById(Long lessonRoomId) {
        Optional<LessonRoom> optionalLessonRoom = lessonRoomDao.findById(lessonRoomId);
        if (optionalLessonRoom.isPresent()) {
            return optionalLessonRoom.get();
        } else {
            logger.warn("LessonRoom with id = {} not found", lessonRoomId);
            throw new ServiceException("LessonRoom with id = " + lessonRoomId + " not found");
        }
    }
}
