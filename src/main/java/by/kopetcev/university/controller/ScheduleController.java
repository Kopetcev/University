package by.kopetcev.university.controller;

import by.kopetcev.university.model.Group;
import by.kopetcev.university.model.LessonView;
import by.kopetcev.university.model.ScheduleView;
import by.kopetcev.university.service.GroupService;
import by.kopetcev.university.service.LessonService;
import by.kopetcev.university.service.ScheduleViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;

@Controller
public class ScheduleController {

    private final ScheduleViewService scheduleViewService;

    private final LessonService lessonService;

    private final GroupService groupService;

    @Autowired
    public ScheduleController(ScheduleViewService scheduleViewService, LessonService lessonService, GroupService groupService) {
        this.scheduleViewService = scheduleViewService;
        this.lessonService = lessonService;
        this.groupService = groupService;
    }

    @GetMapping("/schedule")
    public String getSchedule(Model model) {

        List<String> dayOfWeek = Arrays.asList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday");
        model.addAttribute("dayOfWeek", dayOfWeek);

        List<Group> groups = groupService.findAll();
        groups.sort(Comparator.comparing(Group::getId));
        model.addAttribute("groups", groups);

        ScheduleView scheduleView = scheduleViewService.map(lessonService.findAll());
        List<Map<Long, List<LessonView>>> schedule = new ArrayList<>();
        schedule.add(scheduleView.getLessons().get("Monday"));
        schedule.add(scheduleView.getLessons().get("Tuesday"));
        schedule.add(scheduleView.getLessons().get("Wednesday"));
        schedule.add(scheduleView.getLessons().get("Thursday"));
        schedule.add(scheduleView.getLessons().get("Friday"));
        model.addAttribute("schedule", schedule);
        return "schedule";
    }
}
