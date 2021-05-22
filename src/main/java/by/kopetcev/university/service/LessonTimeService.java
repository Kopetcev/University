package by.kopetcev.university.service;

import by.kopetcev.university.model.LessonTime;

import java.util.List;
import java.util.Optional;

public interface LessonTimeService {

    LessonTime add(LessonTime lessonTime);

    boolean deleteLessonTime(Long lessonTimeId);

    List<LessonTime> findAll();

    Optional<LessonTime> findById(Long lessonTimeId);
}
