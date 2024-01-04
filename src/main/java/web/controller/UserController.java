package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.model.User;
import web.service.UserService;

import java.util.Optional;

@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String showUsers(Model model) {

        model.addAttribute("users", userService.listUsers());
        return "users";
    }

    @PostMapping("/users/add")
    public String addUser(@RequestParam("user") User user) {
        if (user.getId() == 0) {
           userService.addUser(user);
        } else {
            userService.updateUser(user);
        }

        return "redirect:/users";
    }

    @PostMapping("users/remove/{id}")
    public String removeUser(@RequestParam("id") long id) {
        userService.removeUser(id);
        return "redirect:/users";
    }


}
