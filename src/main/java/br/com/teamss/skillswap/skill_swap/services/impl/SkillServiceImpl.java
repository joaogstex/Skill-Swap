package br.com.teamss.skillswap.skill_swap.services.impl;

import br.com.teamss.skillswap.skill_swap.entities.Skill;
import br.com.teamss.skillswap.skill_swap.repositories.SkillRepository;
import br.com.teamss.skillswap.skill_swap.services.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SkillServiceImpl implements SkillService {

    @Autowired
    private SkillRepository skillRepository;

    public List<Skill> findAll() {
        return skillRepository.findAll();
    }

    public Optional<Skill> findById(Long id) {
        return skillRepository.findById(id);
    }

    public Skill save(Skill skill) {
        return skillRepository.save(skill);
    }

    public void delete(Long id) {
        skillRepository.deleteById(id);
    }
}
