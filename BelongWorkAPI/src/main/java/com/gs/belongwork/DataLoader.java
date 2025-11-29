package com.gs.belongwork;

import com.gs.belongwork.auth.model.Role;
import com.gs.belongwork.auth.model.User;
import com.gs.belongwork.model.Opportunity;
import com.gs.belongwork.model.LearningPath;
import com.gs.belongwork.repository.OpportunityRepository;
import com.gs.belongwork.repository.LearningPathRepository;
import com.gs.belongwork.auth.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class DataLoader implements CommandLineRunner {

    private final UserRepository userRepository;
    private final OpportunityRepository opportunityRepository;
    private final LearningPathRepository learningPathRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public DataLoader(UserRepository userRepository,
                      OpportunityRepository opportunityRepository,
                      LearningPathRepository learningPathRepository,
                      BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.opportunityRepository = opportunityRepository;
        this.learningPathRepository = learningPathRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.findByUsername("admin").isEmpty()) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRoles(Set.of(Role.ROLE_ADMIN));
            userRepository.save(admin);
            System.out.println("Admin created");
        }

        if (opportunityRepository.count() == 0) {
            Opportunity o = new Opportunity();
            o.setTitle("Desenvolvedor Java Jr");
            o.setCompany("Tech Inclusiva");
            o.setDescription("Vaga com adaptação para PCDs");
            o.setRequiredSkills("java,spring,sql");
            o.setAccessible(true);
            opportunityRepository.save(o);
        }

        if (learningPathRepository.count() == 0) {
            LearningPath lp = new LearningPath();
            lp.setName("Introdução ao Desenvolvimento");
            lp.setDescription("Trilha básica para iniciantes em programação");
            learningPathRepository.save(lp);
        }
    }
}
