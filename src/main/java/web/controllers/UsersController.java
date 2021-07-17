package web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.DAO.UsersDAO;
import web.DAO.UsersDAOIml;
import web.model.User;

@Controller
@RequestMapping("/users")
public class UsersController {
    @Autowired
    private UsersDAO userDAO;


    @GetMapping
    public String index(Model model) {
        model.addAttribute("users", userDAO.listOfUser());
        return "index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userDAO.getUserById(id));
        return "show";
    }

    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "new";
    }

    @PostMapping()
    public String create(@ModelAttribute("user") User user) {
        userDAO.addUser(user);
        return "redirect:/users";
    }

}
