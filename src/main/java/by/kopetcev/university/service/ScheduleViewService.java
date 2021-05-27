package by.kopetcev.university.service;

import by.kopetcev.university.model.Lesson;
import by.kopetcev.university.model.ScheduleView;

import java.util.List;

public interface ScheduleViewService {
    ScheduleView map(List<Lesson> lessons);
}
