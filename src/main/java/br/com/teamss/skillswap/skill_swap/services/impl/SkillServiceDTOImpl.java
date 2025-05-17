package br.com.teamss.skillswap.skill_swap.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.teamss.skillswap.skill_swap.dto.SkillDTO;
import br.com.teamss.skillswap.skill_swap.entities.Skill;
import br.com.teamss.skillswap.skill_swap.repositories.SkillRepository;
import br.com.teamss.skillswap.skill_swap.services.SkillServiceDTO;
import jakarta.persistence.EntityNotFoundException;

@Service
public class SkillServiceDTOImpl implements SkillServiceDTO {
    
    @Autowired
    private SkillRepository skillRepository;

    @Override
    public SkillDTO toSkillDTO(Skill skill) {
        return new SkillDTO(skill.getSkillId(), skill.getName(), skill.getCategory(), skill.getLevel());
    }

    @Override
    public List<SkillDTO> findAllDTO() {
        return skillRepository.findAll().stream()
                .map(this::toSkillDTO)
                .collect(Collectors.toList());
    }

    @Override
    public SkillDTO findByIdDTO(Long id) {
        Skill skill = skillRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Skill não encontrada"));
        return toSkillDTO(skill);
    }
}
