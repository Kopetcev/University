package by.kopetcev.university.controller;

import by.kopetcev.university.model.*;
import by.kopetcev.university.service.CourseService;
import by.kopetcev.university.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TeacherController {

    private final UserService userService;

    private final CourseService courseService;

    public TeacherController(UserService userService, CourseService courseService) {
        this.userService = userService;
        this.courseService = courseService;
    }

    @GetMapping("/teacher/{id}")
    public String show(@PathVariable("id") long id, Model model) {
        model.addAttribute("roles", courseService.findByTeacherId(id));
        User teacher = userService.findById(id);
        model.addAttribute("teacher", teacher);
        return "teacher/teacher";
    }

    @GetMapping("/teacher/{id}/edit")
    public String editTeacher(Model model, @PathVariable("id") long id) {
        model.addAttribute("teacher", userService.findById(id));
        List<Course> courses = courseService.findAll();
        List<Course> currentCourses = courseService.findByTeacherId(id);
        courses.removeAll(currentCourses);
        model.addAttribute("courses", courses);
        model.addAttribute("currentCourses", currentCourses);
        model.addAttribute("teacherCourse", new TeacherCourse());
        return "/teacher/teacher-edit";
    }

    @PatchMapping("/teacher/{id}/edit")
    public String updateTeacher(@ModelAttribute("teacher") Teacher teacher, @PathVariable("id") long id) {
        userService.add(teacher);
        return "redirect:/teacher/" + teacher.getId()+"/edit";
    }

    @PostMapping("/teacher/create")
    public String createTeacher(@ModelAttribute("teacher") Teacher teacher) {
        userService.assignTeacher(teacher.getId());
        return "redirect:/administration";
    }

    @GetMapping("/teacher/new")
    public String newTeacher(Model model) {
        List<User> users = userService.findAll();
        List<User> teacher = userService.findAllTeacher();
        List<User> students = userService.findAllStudent();
        users.removeAll(teacher);
        users.removeAll(students);
        model.addAttribute("users", users);
        model.addAttribute("teacher", new Teacher());
        return "teacher/teacher-create";
    }

    @DeleteMapping("/teacher/{id}/delete")
    public String delete(@PathVariable("id") long id) {
        userService.deleteFromTeacher(id);
        return "redirect:/administration";
    }
}
