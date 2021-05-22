package by.kopetcev.university.service;

import by.kopetcev.university.model.LessonRoom;

import java.util.List;
import java.util.Optional;

public interface LessonRoomService {

    LessonRoom add(LessonRoom lessonRoom);

    boolean deleteLessonRoom(Long lessonRoomId);

    List<LessonRoom> findAll();

    Optional<LessonRoom> findById(Long lessonRoomId);
}
