package by.kopetcev.university.service;

import by.kopetcev.university.model.LessonRoom;

import java.util.List;

public interface LessonRoomService {

    LessonRoom add(LessonRoom lessonRoom);

    boolean deleteLessonRoom(Long lessonRoomId);

    List<LessonRoom> findAll();

    LessonRoom findById(Long lessonRoomId);
}
