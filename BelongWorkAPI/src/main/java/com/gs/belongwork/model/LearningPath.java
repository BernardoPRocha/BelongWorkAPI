package com.gs.belongwork.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "learning_paths")
public class LearningPath {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(length = 2000)
    private String description;

    @ElementCollection
    private List<String> modules;

    public LearningPath() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public List<String> getModules() { return modules; }
    public void setModules(List<String> modules) { this.modules = modules; }
}
