package br.com.teamss.skillswap.skill_swap.services.impl;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.teamss.skillswap.skill_swap.dto.ProfileDTO;
import br.com.teamss.skillswap.skill_swap.dto.SkillDTO;
import br.com.teamss.skillswap.skill_swap.dto.UserDTO;
import br.com.teamss.skillswap.skill_swap.entities.Profile;
import br.com.teamss.skillswap.skill_swap.entities.Role;
import br.com.teamss.skillswap.skill_swap.entities.User;
import br.com.teamss.skillswap.skill_swap.repositories.UserRepository;
import br.com.teamss.skillswap.skill_swap.services.UserServiceDTO;
import jakarta.persistence.EntityNotFoundException;

@Service
public class UserServiceDTOImpl implements UserServiceDTO {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDTO toUserDTO(User user) {
        // profileDTO
        ProfileDTO profileDTO = null;
        if (user.getProfile() != null) {
            Profile profile = user.getProfile();
            profileDTO = new ProfileDTO(
                    profile.getProfileId(),
                    profile.getDescription(),
                    profile.getImageUrl(),
                    profile.getLocation(),
                    profile.getContactInfo(),
                    profile.getSocialMediaLinks(),
                    profile.getAvailabilityStatus(),
                    profile.getInterests(),
                    profile.getExperienceLevel(),
                    profile.getEducationLevel());
        }

        // roles
        Set<String> roles = user.getRoles().stream()
                .map(Role::getName)
                .collect(Collectors.toSet());

        // skills
        Set<SkillDTO> skills = user.getSkills().stream()
                .map(skill -> new SkillDTO(skill.getSkillId(), skill.getName(), skill.getCategory(), skill.getLevel()))
                .collect(Collectors.toSet());

        return new UserDTO(
                user.getUserId(),
                user.getUsername(),
                roles,
                profileDTO,
                skills);
    }

    @Override
    public List<UserDTO> findAllDTO() {
        return userRepository.findAll().stream()
                .map(this::toUserDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO findByIdDTO(UUID id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));
        return toUserDTO(user);
    }

}
