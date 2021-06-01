package by.kopetcev.university.controller;

import by.kopetcev.university.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index(Model model){
     model.addAttribute("user", new User());
        return "index";
    }
}

