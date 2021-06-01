package by.kopetcev.university.controller;


import by.kopetcev.university.model.Course;
import by.kopetcev.university.model.Lesson;
import by.kopetcev.university.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static java.util.Map.entry;

@Controller
public class LessonController {

    private final LessonViewService lessonViewService;

    private final LessonService lessonService;

    private final CourseService courseService;

    private final UserService userService;

    private final LessonRoomService lessonRoomService;

    private final LessonTimeService lessonTimeService;

    private final GroupService groupService;

    @Autowired
    public LessonController(LessonViewService lessonViewService, LessonService lessonService, CourseService courseService, UserService userService, LessonRoomService lessonRoomService, LessonTimeService lessonTimeService, GroupService groupService) {
        this.lessonViewService = lessonViewService;
        this.lessonService = lessonService;
        this.courseService = courseService;
        this.userService = userService;
        this.lessonRoomService = lessonRoomService;
        this.lessonTimeService = lessonTimeService;
        this.groupService = groupService;
    }

    @GetMapping("/lesson/{id}")
    public String show(@PathVariable("id") long id, Model model) {
        model.addAttribute("lesson", lessonViewService.map(lessonService.findById(id)));
        return "/lesson/lesson";
    }

    @GetMapping("/lesson/new")
    public String newLesson(Model model) {
        model.addAttribute("lesson", new Lesson());
        model.addAttribute("courses", courseService.findAll());
        model.addAttribute("teachers", userService.findAllTeacher());
        model.addAttribute("groups", groupService.findAll());
        model.addAttribute("lessonRooms", lessonRoomService.findAll());
        model.addAttribute("times", lessonTimeService.findAll());
        Map<String, Long> dayOfWeek = Map.ofEntries(
                entry("Monday", 1L),
                entry("Tuesday", 2L),
                entry("Wednesday", 3L),
                entry("Thursday", 4L),
                entry("Friday", 5L)
        );
        model.addAttribute("dayOfWeek", dayOfWeek);
        return "/lesson/lesson-create";
    }

    @PostMapping("/lesson/create")
    public String createLesson(@ModelAttribute("lesson") Lesson lesson) {
        lessonService.add(lesson);
        return "redirect:/administration";
    }

    @GetMapping("/lesson/{id}/edit")
    public String editLesson(Model model, @PathVariable("id") long id) {
        model.addAttribute("courses", courseService.findAll());
        model.addAttribute("teachers", userService.findAllTeacher());
        model.addAttribute("groups", groupService.findAll());
        model.addAttribute("lessonRooms", lessonRoomService.findAll());
        model.addAttribute("times", lessonTimeService.findAll());
        Map<String, Long> dayOfWeek = Map.ofEntries(
                entry("Monday", 1L),
                entry("Tuesday", 2L),
                entry("Wednesday", 3L),
                entry("Thursday", 4L),
                entry("Friday", 5L)
        );
        model.addAttribute("dayOfWeek", dayOfWeek);
        model.addAttribute("lesson", lessonService.findById(id));
        return "/lesson/lesson-edit";
    }

    @PatchMapping("/lesson/{id}/edit")
    public String updateCourse(@ModelAttribute("lesson") Lesson lesson, @PathVariable("id") long id) {
        lessonService.add(lesson);
        return "redirect:administration";
    }

    @DeleteMapping("/lesson/{id}/delete")
    public String delete(@PathVariable("id") long id){
        lessonService.deleteLesson(id);
        return "redirect:/administration";
    }
}
