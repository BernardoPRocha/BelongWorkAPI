package com.gs.belongwork.controller;

import com.gs.belongwork.dto.CandidateDTO;
import com.gs.belongwork.model.Candidate;
import com.gs.belongwork.payload.ApiResponse;
import com.gs.belongwork.service.CandidateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/candidates")
public class CandidateController {

    private final CandidateService service;
    public CandidateController(CandidateService service) { this.service = service; }

    @PostMapping
    public ResponseEntity<ApiResponse<Candidate>> create(@RequestBody CandidateDTO dto) {
        Candidate created = service.create(dto);
        return ResponseEntity.ok(new ApiResponse<>("Candidato criado com sucesso", created));
    }

    @GetMapping
    public ResponseEntity<List<Candidate>> list() {
        return ResponseEntity.ok(service.list());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Candidate>> update(@PathVariable Long id, @RequestBody CandidateDTO dto) {
        Candidate updated = service.update(id, dto);
        if (updated == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(new ApiResponse<>("Candidato atualizado com sucesso", updated));
    }
}
