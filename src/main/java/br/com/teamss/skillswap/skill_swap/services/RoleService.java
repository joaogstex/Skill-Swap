package br.com.teamss.skillswap.skill_swap.services;

import br.com.teamss.skillswap.skill_swap.entities.Role;
import java.util.List;
import java.util.Optional;

public interface RoleService {
    List<Role> findAll();
    Optional<Role> findById(Long id);
    Role save(Role role);
    void delete(Long id);
}
