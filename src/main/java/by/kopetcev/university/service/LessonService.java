package by.kopetcev.university.service;

import by.kopetcev.university.model.Lesson;

import java.util.List;
import java.util.Optional;

public interface LessonService {

    Lesson add(Lesson lesson);

    boolean deleteLesson(Long lessonId);

    List<Lesson> findAll();

    Optional<Lesson> findById(Long lessonId);
}
