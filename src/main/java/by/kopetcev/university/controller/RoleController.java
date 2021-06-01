package by.kopetcev.university.controller;

import by.kopetcev.university.model.Course;
import by.kopetcev.university.model.UserRole;
import by.kopetcev.university.service.CourseService;
import by.kopetcev.university.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RoleController {

    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping("/role/assign")
    public String assignRole(@ModelAttribute("userRole") UserRole userRole) {
        roleService.assignUser(userRole.getRoleId(), userRole.getUserId());
        return "redirect:/user/" + userRole.getUserId() + "/edit";
    }

    @DeleteMapping("/role/{id}/delete-from/{userId}")
    public String delete(@PathVariable("id") long id, @PathVariable("userId") long userId){
        roleService.deleteByIdFromUser(id, userId);
        return "redirect:/user/" + userId + "/edit";
    }
}


        /*
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
 */

