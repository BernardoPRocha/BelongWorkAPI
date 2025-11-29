package com.gs.belongwork.service;

import com.gs.belongwork.model.Candidate;
import com.gs.belongwork.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gs.belongwork.dto.CandidateDTO;
import com.gs.belongwork.model.vo.SkillVO;

import java.util.List;
import java.util.Optional;

@Service
public class CandidateService {

    @Autowired
    private CandidateRepository repo;

    public Candidate create(CandidateDTO dto) {
        Candidate c = new Candidate();
        c.setName(dto.getName());
        c.setEmail(dto.getEmail());
        c.setDisability(dto.getDisability());
        if (dto.getSkills() != null) {
            List<SkillVO> voSkills = dto.getSkills()
                    .stream()
                    .map(SkillVO::new)
                    .toList();
            c.setSkills(voSkills);
        }
        return repo.save(c);
    }


    public List<Candidate> list() {
        return repo.findAll();
    }

    public Optional<Candidate> findById(Long id) {
        return repo.findById(id);
    }

    public Candidate update(Long id, CandidateDTO dto) {
        Candidate c = repo.findById(id).orElse(null);
        if (c == null) return null;

        c.setName(dto.getName());
        c.setEmail(dto.getEmail());
        c.setDisability(dto.getDisability());
        c.setSkills(
                dto.getSkills().stream().map(SkillVO::new).toList()
        );

        return repo.save(c);
    }
}

