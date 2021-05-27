package by.kopetcev.university.controller;


import by.kopetcev.university.service.LessonService;
import by.kopetcev.university.service.LessonViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class LessonController {

    private final LessonViewService lessonViewService;

    private final LessonService lessonService;

    @Autowired
    public LessonController(LessonViewService lessonViewService, LessonService lessonService) {
        this.lessonViewService = lessonViewService;
        this.lessonService = lessonService;

    }
    @GetMapping("/lesson/{id}")
    public String show(@PathVariable("id") long id, Model model) {
        model.addAttribute("lesson", lessonViewService.map(lessonService.findById(id)));
        return "lesson";
    }
}
