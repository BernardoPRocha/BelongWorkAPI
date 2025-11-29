package com.gs.belongwork.service;

import com.gs.belongwork.model.vo.SkillVO;
import com.gs.belongwork.model.Candidate;
import com.gs.belongwork.model.Opportunity;
import com.gs.belongwork.repository.CandidateRepository;
import com.gs.belongwork.repository.OpportunityRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class RecommendationService {
    private final CandidateRepository candidateRepo;
    private final OpportunityRepository oppRepo;

    public RecommendationService(CandidateRepository candidateRepo, OpportunityRepository oppRepo) {
        this.candidateRepo = candidateRepo;
        this.oppRepo = oppRepo;
    }

    /**
     * Método principal: recebe um Candidate e retorna lista de oportunidades recomendadas.
     */
    public List<Opportunity> recommendForCandidate(Candidate candidate) {
        if (candidate == null) return Collections.emptyList();

        Set<String> candSkills = candidateSkillNamesAsSet(candidate);

        return oppRepo.findAll().stream()
                // opcional: calcular score e ordenar
                .map(op -> new AbstractMap.SimpleEntry<>(op, scoreMatch(op, candSkills)))
                .filter(entry -> entry.getValue() > 0) // por exemplo, descarta matches com score 0
                .sorted((a,b) -> Integer.compare(b.getValue(), a.getValue())) // decrescente por score
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }


    public List<Opportunity> recommendForCandidate(Long candidateId, int maxResults) {
        if (candidateId == null) return Collections.emptyList();
        Optional<Candidate> candOpt = candidateRepo.findById(candidateId);
        if (candOpt.isEmpty()) return Collections.emptyList();
        List<Opportunity> all = recommendForCandidate(candOpt.get());
        if (maxResults <= 0) return all;
        return all.stream().limit(maxResults).collect(Collectors.toList());
    }

    private int scoreMatch(Opportunity o, Set<String> skills) {
        if (o == null) return 0;
        if (o.getRequiredSkills() == null || o.getRequiredSkills().isBlank()) return 0;

        String[] req = o.getRequiredSkills().split(",");
        int score = 0;
        for (String r : req) {
            String trimmed = r.trim().toLowerCase();
            if (trimmed.isEmpty()) continue;
            if (skills.contains(trimmed)) score++;
        }
        if (o.isAccessible()) score += 1;
        return score;
    }

    private Set<String> candidateSkillNamesAsSet(Candidate candidate) {
        if (candidate == null || candidate.getSkills() == null) {
            return Collections.emptySet();
        }
        return candidate.getSkills()
                .stream()
                .map(SkillVO::getName)
                .filter(Objects::nonNull)
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .map(String::toLowerCase)   // normaliza pra comparação
                .collect(Collectors.toSet());
    }


    private Set<String> parseOpportunitySkillsAsSet(Opportunity op) {
        if (op == null || op.getRequiredSkills() == null || op.getRequiredSkills().isBlank())
            return Collections.emptySet();

        return Arrays.stream(op.getRequiredSkills().split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .map(String::toLowerCase)
                .collect(Collectors.toSet());
    }
}
