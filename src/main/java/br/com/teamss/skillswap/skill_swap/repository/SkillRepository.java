package br.com.teamss.skillswap.skill_swap.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.teamss.skillswap.skill_swap.entities.Skill;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Long> {}
