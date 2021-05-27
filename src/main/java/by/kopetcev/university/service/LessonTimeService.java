package by.kopetcev.university.service;

import by.kopetcev.university.model.LessonTime;

import java.util.List;

public interface LessonTimeService {

    LessonTime add(LessonTime lessonTime);

    boolean deleteLessonTime(Long lessonTimeId);

    List<LessonTime> findAll();

    LessonTime findById(Long lessonTimeId);
}
