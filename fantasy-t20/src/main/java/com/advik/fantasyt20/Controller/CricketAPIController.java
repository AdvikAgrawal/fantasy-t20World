package com.advik.fantasyt20.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.advik.fantasyt20.Model.CricketAPIService;

@RestController
@RequestMapping("/api")
public class CricketAPIController {
    private final CricketAPIService cricketAPIService;
    
    public CricketAPIController(CricketAPIService cricketAPIService) {
        this.cricketAPIService = cricketAPIService;
    }
    @GetMapping("/fixtures")
    public String getFixtures() {
        return cricketAPIService.getUpcomingFixtures();
    }
}
