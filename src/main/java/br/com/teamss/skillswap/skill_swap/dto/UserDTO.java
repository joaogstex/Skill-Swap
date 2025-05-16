package br.com.teamss.skillswap.skill_swap.dto;

import java.util.Set;
import java.util.UUID;

public record UserDTO(
    UUID userId,
    String username,
    Set<String> roles,
    ProfileDTO profile,
    Set<SkillDTO> skills
) {}
