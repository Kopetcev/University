package by.kopetcev.university.model;

import java.util.List;
import java.util.Map;

public class ScheduleView {

    private Map<String,Map<Long, List<LessonView>>> lessons;



    public ScheduleView(Map<String,Map<Long, List<LessonView>>> lessons) {
        this.lessons = lessons;

    }

    public Map<String,Map<Long, List<LessonView>>> getLessons() {
        return lessons;
    }

    public void setLessons(Map<String,Map<Long, List<LessonView>>> lessons) {
        this.lessons = lessons;
    }
}
