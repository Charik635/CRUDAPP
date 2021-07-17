package web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

@Controller
@RequestMapping("/users")
public class UsersController {
    @Autowired
    private UserService userService;


    @GetMapping
    public String index(Model model) {
        model.addAttribute("users", userService.listOfUser());
        return "index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "show";
    }

    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "new";
    }

    @PostMapping()
    public String create(@ModelAttribute("user") User user) {
        userService.addUser(user);
        return "redirect:/users";
    }

}
