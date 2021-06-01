package by.kopetcev.university.controller;

import by.kopetcev.university.model.Course;
import by.kopetcev.university.model.TeacherCourse;
import by.kopetcev.university.model.UserRole;
import by.kopetcev.university.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CourseController {

    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/course/{id}")
    public String show(@PathVariable("id") long id, Model model) {
        model.addAttribute("course", courseService.findById(id));
        return "course/course";
    }

    @GetMapping("/course/new")
    public String newCourse(Model model) {
        model.addAttribute("course", new Course());
        return "course/course-create";
    }

    @GetMapping("/course/{id}/edit")
    public String editCourse(Model model, @PathVariable("id") long id) {
       model.addAttribute("course", courseService.findById(id));
        return "/course/course-edit";
    }

    @PatchMapping("/course/{id}/edit")
    public String updateCourse(@ModelAttribute("course") Course course, @PathVariable("id") long id) {
        courseService.add(course);
        return "redirect:/course/"+course.getId();
    }


    @PostMapping("/course/create")
    public String createCourse(@ModelAttribute("course") Course course) {
        courseService.add(course);
        return "redirect:/administration";
    }

    @DeleteMapping("/course/{id}/delete")
    public String delete(@PathVariable("id") long id){
        courseService.deleteCourse(id);
    return "redirect:/administration";
    }

    @DeleteMapping("/course/{id}/delete-from/{teacherId}")
    public String deleteFromTeacher(@PathVariable("id") long id, @PathVariable("teacherId") long teacherId){
        courseService.deleteByIdFromTeacher(id, teacherId);
        return "redirect:/teacher/" + teacherId + "/edit";
    }

    @PostMapping("/course/assign")
    public String assignTeacher(@ModelAttribute("userRole") TeacherCourse teacherCourse) {
        courseService.assignTeacher(teacherCourse.getCourseId(), teacherCourse.getTeacherId());
        return "redirect:/teacher/" + teacherCourse.getTeacherId() + "/edit";
    }

}


