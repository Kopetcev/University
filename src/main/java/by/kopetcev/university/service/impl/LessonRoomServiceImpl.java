package by.kopetcev.university.service.impl;

import by.kopetcev.university.dao.LessonRoomDao;
import by.kopetcev.university.model.LessonRoom;
import by.kopetcev.university.service.LessonRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class LessonRoomServiceImpl implements LessonRoomService {

    private final LessonRoomDao lessonRoomDao;

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
    public Optional<LessonRoom> findById(Long lessonRoomId) {
        return lessonRoomDao.findById(lessonRoomId);
    }
}
