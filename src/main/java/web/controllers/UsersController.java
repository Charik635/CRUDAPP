package web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;
import java.security.Principal;

@Controller
@RequestMapping("/")
public class UsersController {
    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public String UserHome(Model model, Principal principal) {
        User user = (User) userService.loadUserByUsername(principal.getName());
        model.addAttribute("user", user);
        return "homepage";
    }


}
