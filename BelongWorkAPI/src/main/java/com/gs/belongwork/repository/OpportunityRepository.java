package com.gs.belongwork.repository;

import com.gs.belongwork.model.Opportunity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OpportunityRepository extends JpaRepository<Opportunity, Long> {}
