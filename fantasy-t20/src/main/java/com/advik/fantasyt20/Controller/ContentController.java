package com.advik.fantasyt20.Controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.advik.fantasyt20.Model.MyAppUser;

@Controller
public class ContentController {
    
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("user", new MyAppUser());
        return "signup";
    }

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/leaderboard")
    public String leaderboard() {
        return "leaderboard";
    }

    @GetMapping("/teambuilder")
    public String teamBuilder() {
        return "teambuilder";
    }

}
