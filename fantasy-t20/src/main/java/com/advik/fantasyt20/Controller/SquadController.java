package com.advik.fantasyt20.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.advik.fantasyt20.Model.MyAppUser;
import com.advik.fantasyt20.Model.MyAppUserRepository;
import com.advik.fantasyt20.Model.Players;
import com.advik.fantasyt20.Model.PlayersRepository;
import com.advik.fantasyt20.Model.Squads;
import com.advik.fantasyt20.Model.SquadsRepository;
import com.advik.fantasyt20.Model.UserTeamPlayers;
import com.advik.fantasyt20.Model.UserTeamPlayersRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class SquadController {
    @Autowired
    private SquadsRepository squadsRepository;

    @Autowired
    private PlayersRepository playersRepository;

    @Autowired
    private UserTeamPlayersRepository userTeamPlayersRepository;

    @Autowired
    private MyAppUserRepository myAppUserRepository;
    @GetMapping("/teams")
    public List<Squads> getTeams() {
        return squadsRepository.findAll();
    }
    
    @GetMapping("/squads")
    public List<Players> getSquad(@RequestParam Long squadId) {
        return playersRepository.findBySquadId(squadId);
    }
    
    @GetMapping("/players")
    public List<Players> getPlayers() {
        return playersRepository.findAll();
    }

    @Transactional
    @PostMapping("/my-team/update")
    public ResponseEntity<?> updateMyTeam(Principal principal, @RequestBody List<UserTeamPlayers> userTeamPlayers) {
        
        String email = principal.getName(); 
        MyAppUser user = myAppUserRepository.findByEmail(email);
        if (user == null) return ResponseEntity.status(404).body("User not found");
        userTeamPlayersRepository.deleteByMyAppUserEmail(email);

        for (UserTeamPlayers utp : userTeamPlayers) {
            utp.setMyAppUser(user);
        }
        
        userTeamPlayersRepository.saveAll(userTeamPlayers);
        return ResponseEntity.ok("Saved using Email as FK!");
    }


}