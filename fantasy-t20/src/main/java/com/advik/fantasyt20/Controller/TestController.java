package com.advik.fantasyt20.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.advik.fantasyt20.Model.PointsService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class TestController {
    private final PointsService pointsService;

    @GetMapping("/test/runScoring")
    public String runScoring() {
        pointsService.processTestRun();
        return "Dummy executed";
    }
}
