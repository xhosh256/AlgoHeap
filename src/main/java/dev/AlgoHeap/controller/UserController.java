package dev.AlgoHeap.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import dev.AlgoHeap.model.User;
import dev.AlgoHeap.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/users")
public class UserController {

    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String registerPage(Model model) {

        model.addAttribute("user", new User());
        return "users/registerPage";
    }

    @GetMapping("/login")
    public String loginPage(Model model) {

        model.addAttribute("user", new User());
        return "users/loginPage";
    }

    @GetMapping("logout")
    public String logout(
        HttpSession session
    ) {
        session.removeAttribute("user");

        return "redirect:/";
    }

    @PostMapping("/register")
    public String processRegister(
        @ModelAttribute("user") User user,
        HttpSession session
    ) {

        session.setAttribute("user", userService.saveUser(user));
        return "redirect:/";
    }

    @PostMapping("/login")
    public String processLogin(
        @ModelAttribute User userForm, Errors errors,
        HttpSession session
    ) {
        
        User user = userService.findByNameAndPassword(userForm.getName(), userForm.getPassword());

        if(user == null) {
            errors.rejectValue("name", "error.user", "Incorrect username or password");
            errors.rejectValue("password", "error.user", "Incorrect username or password");
            return "loginPage";
        }

        session.setAttribute("user", user);
        return "redirect:/";
    }
}
