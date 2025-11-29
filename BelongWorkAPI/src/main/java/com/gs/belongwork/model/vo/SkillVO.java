package com.gs.belongwork.model.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class SkillVO {
    @Column(name = "skill_name")
    private String name;

    public SkillVO() {}

    public SkillVO(String name) { this.name = name; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
