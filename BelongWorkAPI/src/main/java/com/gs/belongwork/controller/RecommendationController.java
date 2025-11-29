package com.gs.belongwork.controller;

import com.gs.belongwork.model.Opportunity;
import com.gs.belongwork.service.RecommendationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recommendations")
public class RecommendationController {
    private final RecommendationService service;
    public RecommendationController(RecommendationService service){ this.service = service; }

    @GetMapping("/candidate/{id}")
    public ResponseEntity<List<Opportunity>> recommend(@PathVariable Long id, @RequestParam(defaultValue = "5") int top){
        return ResponseEntity.ok(service.recommendForCandidate(id, top));
    }
}
