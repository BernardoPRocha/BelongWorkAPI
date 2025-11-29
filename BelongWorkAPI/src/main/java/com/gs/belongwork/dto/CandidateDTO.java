package com.gs.belongwork.dto;

import java.util.List;

public class CandidateDTO {
    private String name;
    private String email;
    private String disability;
    private List<String> skills; // no DTO é mais simples manter List<String>

    public CandidateDTO() {}

    // getters e setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getDisability() { return disability; }
    public void setDisability(String disability) { this.disability = disability; }

    public List<String> getSkills() { return skills; }
    public void setSkills(List<String> skills) { this.skills = skills; }
}
