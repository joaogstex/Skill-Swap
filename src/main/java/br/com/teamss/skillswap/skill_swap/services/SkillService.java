package br.com.teamss.skillswap.skill_swap.services;

import br.com.teamss.skillswap.skill_swap.entities.Skill;
import java.util.List;
import java.util.Optional;

public interface SkillService {
    List<Skill> findAll();
    Optional<Skill> findById(Long id);
    Skill save(Skill skill);
    void delete(Long id);
}
