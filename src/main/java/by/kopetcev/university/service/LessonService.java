package by.kopetcev.university.service;

import by.kopetcev.university.model.Lesson;

import java.util.List;

public interface LessonService {

    Lesson add(Lesson lesson);

    boolean deleteLesson(Long lessonId);

    List<Lesson> findAll();

    Lesson findById(Long lessonId);
}
