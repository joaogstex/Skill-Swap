package br.com.teamss.skillswap.skill_swap.services;

import java.util.List;

import br.com.teamss.skillswap.skill_swap.dto.RoleDTO;
import br.com.teamss.skillswap.skill_swap.entities.Role;

public interface RoleServiceDTO {
    RoleDTO toRoleDTO(Role role);
    List<RoleDTO> findAllDTO();
    RoleDTO findByIdDTO(Long id);
}
