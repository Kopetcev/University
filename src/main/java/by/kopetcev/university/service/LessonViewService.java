package by.kopetcev.university.service;

import by.kopetcev.university.model.Lesson;
import by.kopetcev.university.model.LessonView;

public interface LessonViewService {

    LessonView map(Lesson lesson);
}
