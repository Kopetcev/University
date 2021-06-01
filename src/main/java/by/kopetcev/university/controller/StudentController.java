package by.kopetcev.university.controller;

import by.kopetcev.university.model.*;

import by.kopetcev.university.service.GroupService;
import by.kopetcev.university.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class StudentController {

    private final UserService userService;

    private final GroupService groupService;

    public StudentController(UserService userService, GroupService groupService) {
        this.userService = userService;
        this.groupService = groupService;
    }

    @GetMapping("/student/{id}")
    public String show(@PathVariable("id") long id, Model model) {
        model.addAttribute("student", userService.findById(id));
        return "student/student";
    }

    @GetMapping("/student/{id}/edit")
    public String editStudent(Model model, @PathVariable("id") long id) {
        model.addAttribute("student", userService.findById(id));
        model.addAttribute("groups", groupService.findAll());

        return "/student/student-edit";
    }

    @PatchMapping("/student/{id}/edit")
    public String updateStudent(@ModelAttribute("student") User student, @PathVariable("id") long id) {
        userService.add(student);
        return "redirect:/student/" + student.getId() + "/edit";
    }

    @GetMapping("/student/new")
    public String newStudent(Model model) {
        List<User> users = userService.findAll();
        List<User> teacher = userService.findAllTeacher();
        List<User> students = userService.findAllStudent();
        users.removeAll(teacher);
        users.removeAll(students);
        model.addAttribute("users", users);
        model.addAttribute("groups", groupService.findAll());
        model.addAttribute("student", new Student());

        return "student/student-create";
    }

    @PostMapping("/student/create")
    public String createStudent(@ModelAttribute("student") Student student) {
        userService.assignStudent(student.getId(), student.getGroupId());
        return "redirect:/administration";
    }

    @DeleteMapping("/student/{id}/delete")
    public String delete(@PathVariable("id") long id) {
        userService.deleteFromStudent(id);
        return "redirect:/administration";
    }
}

