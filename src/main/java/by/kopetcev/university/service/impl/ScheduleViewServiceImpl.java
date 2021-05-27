package by.kopetcev.university.service.impl;

import by.kopetcev.university.model.Lesson;
import by.kopetcev.university.model.LessonView;
import by.kopetcev.university.model.ScheduleView;
import by.kopetcev.university.service.LessonViewService;
import by.kopetcev.university.service.ScheduleViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Transactional
@Component
public class ScheduleViewServiceImpl implements ScheduleViewService {


    private final LessonViewService lessonViewService;

    @Autowired
    public ScheduleViewServiceImpl(LessonViewService lessonViewService) {
        this.lessonViewService = lessonViewService;
    }

    @Override
    public ScheduleView map(List<Lesson> lessons) {
        lessons.sort(Comparator.comparing(Lesson::getGroupId));

        Map<String,Map<Long,List<LessonView>>> resultLessons = lessons.stream().map(lessonViewService::map).collect(Collectors.groupingBy(LessonView::getDayOfWeek,  Collectors.groupingBy(lesson ->lesson.getLessonTime().getId())));

        return new ScheduleView(resultLessons);
    }
}
