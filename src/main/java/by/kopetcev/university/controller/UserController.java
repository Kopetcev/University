package by.kopetcev.university.controller;

import by.kopetcev.university.model.*;
import by.kopetcev.university.service.RoleService;
import by.kopetcev.university.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {

    private final UserService userService;

    private final RoleService roleService;

    @Autowired
    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/user/{id}")
    public String show(@PathVariable("id") long id, Model model) {
        model.addAttribute("roles", roleService.findByUserId(id));
        User user = userService.findById(id);
        String type = "User";
        if(user instanceof Student){
            type="Student";
        }else
        if(user instanceof Teacher) {
            type="Teacher";
        }
        model.addAttribute("user", user);
        model.addAttribute("type", type);
        return "user/user";
    }

    @GetMapping("/user/{id}/edit")
    public String editUser(Model model, @PathVariable("id") long id) {
        model.addAttribute("user", userService.findById(id));
        List<Role> roles = roleService.findAll();
        List<Role> currentRoles = roleService.findByUserId(id);
        roles.removeAll(currentRoles);
        model.addAttribute("roles", roles);
        model.addAttribute("currentRoles", currentRoles);
        model.addAttribute("userRole", new UserRole());
        return "/user/user-edit";
    }

    @PatchMapping("/user/{id}/edit")
    public String updateUser(@ModelAttribute("user") User user, @PathVariable("id") long id) {
        userService.add(user);
        return "redirect:/user/" + user.getId()+"/edit";
    }

    @PostMapping("/user/create")
    public String createUser(@ModelAttribute("user") User user) {
        userService.add(user);
        return "redirect:/";
    }

    @DeleteMapping("/user/{id}/delete")
    public String delete(@PathVariable("id") long id) {
        userService.deleteUser(id);
        return "redirect:/administration";
    }
}

