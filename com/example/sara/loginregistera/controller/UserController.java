package com.example.sara.loginregistera.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    @GetMapping("/")
    public String loginRegPage(Model viewModel, HttpSession session) {
        // Prevent logged-in users from visiting the registration page
        if (session.getAttribute("userID") != null) {
            return "redirect:/home";
        }

        viewModel.addAttribute("newUser", new User());
        viewModel.addAttribute("newLogin", new LoginUser());
        return "login";
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("newUser") User newUser, BindingResult result, HttpSession session, Model viewModel) {
        User registeredUser = userServ.validateRegistration(newUser, result);

        if (result.hasErrors()) {
            return "register";
        }

        session.setAttribute("userID", registeredUser.getId());
        session.setAttribute("userRole", registeredUser.getRole());
        
        return "redirect:/home";
    }

    @PostMapping("/login")
    public String loginUser(@Valid @ModelAttribute("newLogin") LoginUser newLogin, BindingResult result, HttpSession session, Model viewModel) {
        User loggedInUser = userServ.validateLogin(newLogin, result);

        if (result.hasErrors()) {
            viewModel.addAttribute("newUser", new User());
            return "login";
        }

        session.setAttribute("userID", loggedInUser.getId());
        session.setAttribute("userRole", loggedInUser.getRole());
        
        return "redirect:/home";
    }
} 