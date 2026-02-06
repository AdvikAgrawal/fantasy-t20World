package com.advik.fantasyt20.Controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.advik.fantasyt20.Model.UserTeamPlayers;
import com.advik.fantasyt20.Model.UserTeamPlayersRepository;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class YourTeamController {

    @Autowired
    private UserTeamPlayersRepository userTeamPlayersRepository; 
    @GetMapping("/yourteam")
    public String myTeamPage(Principal principal, Model model) {
        List<UserTeamPlayers> team = userTeamPlayersRepository.findByMyAppUserEmail(principal.getName());
        model.addAttribute("team", team);

        return "yourteam";
    }

}
