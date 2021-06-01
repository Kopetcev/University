package by.kopetcev.university.controller;

import by.kopetcev.university.model.UserRole;
import by.kopetcev.university.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
