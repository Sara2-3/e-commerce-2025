package com.example.sara.loginregistera.controller;

import com.example.sara.loginregistera.model.LoginUser;
import com.example.sara.loginregistera.model.User;
import com.example.sara.loginregistera.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.logging.Logger;

@Controller
public class UserController {

    @Autowired
    private UserService userServ;

    private final Logger logger = Logger.getLogger(UserController.class.getName());


    @GetMapping("/")
    public String loginRegPage(Model viewModel, HttpSession session) {
        if (session.getAttribute("userID") != null) {
            return "redirect:/dashboard";
        }
        viewModel.addAttribute("newUser", new User());
        viewModel.addAttribute("newLogin", new LoginUser());

        return "login";
    }
    @GetMapping("/register")
    public String showRegisterPage(Model viewModel) {

        viewModel.addAttribute("newUser", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("newUser") User newUser, BindingResult result, HttpSession session, Model viewModel) {
        // Validate registration
        User registeredUser = userServ.validateRegistration(newUser, result);

        if (result.hasErrors()) {
            logger.info("Registration failed: " + result.toString());
            return "register";
        }
        session.setAttribute("userID", registeredUser.getId());
        session.setAttribute("userRole", registeredUser.getRole());
        logger.info("Registered user: " + registeredUser.getId() + " Role: " + registeredUser.getRole());

        return "redirect:/dashboard";
    }

    @PostMapping("/login")
    public String loginUser(@Valid @ModelAttribute("newLogin") LoginUser newLogin, BindingResult result, HttpSession session, Model viewModel) {
        User loggedInUser = userServ.validateLogin(newLogin, result);

        if (result.hasErrors()) {
            logger.info("Login failed: " + result.toString());
            viewModel.addAttribute("newUser", new User());
            return "login";
        }
        session.setAttribute("userID", loggedInUser.getId());
        session.setAttribute("userRole", loggedInUser.getRole());
        logger.info("Logged in user: " + loggedInUser.getId() + " Role: " + loggedInUser.getRole());

        return "redirect:/dashboard";
    }
    @GetMapping("/dashboard")
    public String dashboardPage(HttpSession session, Model viewModel) {
        Object userIDObj = session.getAttribute("userID");
        if (userIDObj == null) {
            logger.info("No user logged in. Redirecting to login page.");
            return "redirect:/";
        }

        Long userID = (Long) userIDObj;
        User loggedInUser = userServ.getUserById(userID);
        if (loggedInUser == null) {
            logger.info("User not found in database. Redirecting to login page.");
            return "redirect:/";
        }
        logger.info("Current user: " + loggedInUser.getId() + " Role: " + loggedInUser.getRole());

        viewModel.addAttribute("loggedInUser", loggedInUser);
        viewModel.addAttribute("userRole", loggedInUser.getRole());

        return "dashboard";
    }

    @PostMapping("/logout")
    public String logoutUser(HttpSession session) {
        logger.info("Logging out user with session: " + session.getAttribute("userID"));
        session.invalidate();
        return "redirect:/";
    }
}