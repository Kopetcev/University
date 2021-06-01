package by.kopetcev.university.controller;

import by.kopetcev.university.model.Group;
import by.kopetcev.university.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class GroupController {

    private final GroupService groupService;

    @Autowired
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping("/group/{id}")
    public String show(@PathVariable("id") long id, Model model) {
        model.addAttribute("group", groupService.findById(id));
        return "group/group";
    }

    @GetMapping("/group/new")
    public String newGroup(Model model) {
        model.addAttribute("group", new Group());
        return "group/group-create";
    }

    @GetMapping("/group/{id}/edit")
    public String editGroup(Model model, @PathVariable("id") long id) {
        model.addAttribute("group", groupService.findById(id));
        return "/group/group-edit";
    }

    @PatchMapping("/group/{id}/edit")
    public String updateGroup(@ModelAttribute("group") Group group) {
        groupService.add(group);
        return "redirect:/group/"+group.getId();
    }


    @PostMapping("/group/create")
    public String createGroup(@ModelAttribute("group") Group group) {
        groupService.add(group);
        return "redirect:/administration";
    }

    @DeleteMapping("/group/{id}/delete")
    public String delete(@PathVariable("id") long id){
        groupService.deleteGroup(id);
        return "redirect:/administration";
    }
}
