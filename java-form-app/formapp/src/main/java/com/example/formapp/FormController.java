package com.example.formapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class FormController {

    @Autowired // Injects the UserRepository
    private UserRepository userRepository;

    // Handles GET requests to /form, displays the form and a list of existing users
    @GetMapping("/form")
    public String showForm(Model model) {
        model.addAttribute("message", "Please enter your name:");
        // Fetch all users from the database
        List<User> users = userRepository.findAll();
        model.addAttribute("users", users); // Add the list of users to the model
        return "form"; // Refers to src/main/resources/templates/form.html
    }

    // Handles POST requests to /submit, processes the form data and saves it to the database
    @PostMapping("/submit")
    public String submitForm(@RequestParam(name = "userName") String userName, Model model) {
        // Create a new User object
        User newUser = new User(userName);
        // Save the new user to the database
        userRepository.save(newUser);

        model.addAttribute("submittedName", userName);
        return "result"; // Refers to src/main/resources/templates/result.html
    }
}
