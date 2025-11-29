package com.gs.belongwork.model;

import com.gs.belongwork.model.vo.SkillVO;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "candidates")
public class Candidate {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String disability;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "candidate_skills", joinColumns = @JoinColumn(name = "candidate_id"))
    // sem @Column aqui porque SkillVO tem coluna própria; se fosse String, usaria @Column(name="skill")
    private List<SkillVO> skills;

    public Candidate() {}

    // getters / setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getDisability() { return disability; }
    public void setDisability(String disability) { this.disability = disability; }

    public List<SkillVO> getSkills() { return skills; }
    public void setSkills(List<SkillVO> skills) { this.skills = skills; }
}
