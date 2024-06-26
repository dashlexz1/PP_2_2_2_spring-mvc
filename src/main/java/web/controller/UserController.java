package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.models.User;
import web.service.UserService;


@Controller
public class UserController {
    @Autowired
    private UserService userService;


    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.listUsers());
        return "users";
    }
    @GetMapping("/users/new")
    public String createUserGet(@ModelAttribute("user") User user) {
        return "users";
    }

    @PostMapping("/users/сreateUser")
    public String addUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "users";
    }

    @GetMapping("/users/removeUser")
    public String removeUser(@RequestParam(value = "id", required = false) Long id) {
        userService.deleteById(id);
        return "users";
    }

    @GetMapping("/users/updateUser/edit")
    public String editUserGet(@RequestParam(value = "id", required = false) Long id, Model model) {
        model.addAttribute("user", userService.getUser(id));
        return "users";
    }

    @PostMapping("/users/updateUser")
    public String postEditUserForm(@ModelAttribute("user") User user) {
        userService.updateUser(user);
        return "users";
    }
}


