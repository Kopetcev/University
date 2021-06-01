package by.kopetcev.university.controller;

import by.kopetcev.university.model.*;
import by.kopetcev.university.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@Controller
public class AdministrationController {

    private final UserService userService;

    private final ScheduleViewService scheduleViewService;

    private final LessonService lessonService;

    private final GroupService groupService;

    private final LessonViewService lessonViewService;

    private final CourseService courseService;

    @Autowired
    public AdministrationController(UserService userService,  ScheduleViewService scheduleViewService, LessonService lessonService, GroupService groupService, LessonViewService lessonViewService, CourseService courseService) {
        this.userService = userService;
        this.scheduleViewService = scheduleViewService;
        this.lessonService = lessonService;
        this.groupService = groupService;
        this.lessonViewService = lessonViewService;
        this.courseService = courseService;
    }

    @GetMapping("/administration")
    public String getAdministarion(Model model) {

        List<User> users = userService.findAll();
        users.sort(Comparator.comparing(User::getFirstName));
        model.addAttribute("users", users);

        List<User> teachers = userService.findAllTeacher();
        teachers.sort(Comparator.comparing(User::getFirstName));
        model.addAttribute("teachers", teachers);

        List<User> students = userService.findAllStudent();
        students.sort(Comparator.comparing(User::getFirstName));
        model.addAttribute("students", students);

        List<String> dayOfWeek = Arrays.asList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday");
        model.addAttribute("dayOfWeek", dayOfWeek);

        List<Group> groups = groupService.findAll();
        groups.sort(Comparator.comparing(Group::getId));
        Map<Long, List<Group>> mapGroups = new TreeMap<>(groups.stream().collect(Collectors.groupingBy(Group::getId)));
        model.addAttribute("groups", mapGroups);

        List<LessonView> lessons = lessonService.findAll().stream().map(lessonViewService::map).collect(Collectors.toList());
        model.addAttribute("lessons", lessons);

        ScheduleView scheduleView = scheduleViewService.map(lessonService.findAll());
        List<Map<Long, List<LessonView>>> schedule = new ArrayList<>();
        schedule.add(scheduleView.getLessons().get("Monday"));
        schedule.add(scheduleView.getLessons().get("Tuesday"));
        schedule.add(scheduleView.getLessons().get("Wednesday"));
        schedule.add(scheduleView.getLessons().get("Thursday"));
        schedule.add(scheduleView.getLessons().get("Friday"));
        model.addAttribute("schedule", schedule);

        model.addAttribute("courses", courseService.findAll());

        return "administration";
    }

}
