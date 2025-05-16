package br.com.teamss.skillswap.skill_swap.services;

import java.util.List;
import java.util.UUID;

import br.com.teamss.skillswap.skill_swap.dto.UserDTO;
import br.com.teamss.skillswap.skill_swap.entities.User;

public interface UserServiceDTO {
    public UserDTO toUserDTO(User user);
    public List<UserDTO> findAllDTO();
    public UserDTO findByIdDTO(UUID id);
}
