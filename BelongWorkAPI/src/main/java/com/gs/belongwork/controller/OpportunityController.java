package com.gs.belongwork.controller;

import com.gs.belongwork.model.Opportunity;
import com.gs.belongwork.repository.OpportunityRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/opportunities")
public class OpportunityController {
    private final OpportunityRepository repo;
    public OpportunityController(OpportunityRepository repo){ this.repo = repo; }

    @PostMapping
    public ResponseEntity<Opportunity> create(@RequestBody Opportunity o){ return ResponseEntity.ok(repo.save(o)); }

    @GetMapping
    public ResponseEntity<List<Opportunity>> list(){ return ResponseEntity.ok(repo.findAll()); }

    @GetMapping("/{id}")
    public ResponseEntity<Opportunity> get(@PathVariable Long id){
        return repo.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}
