package com.advik.fantasyt20.Controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.advik.fantasyt20.Model.MyAppUser;
import com.advik.fantasyt20.Model.MyAppUserRepository;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class SignUpController {
    private final MyAppUserRepository repository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/signup")
    public String registerUser(@ModelAttribute MyAppUser user, Model model) {
        if (repository.existsByEmail(user.getEmail())) {
            model.addAttribute("error", "Email already taken!");
            return "signup";
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        repository.save(user);
        return "redirect:/login";
    }
}
