package br.com.teamss.skillswap.skill_swap.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.teamss.skillswap.skill_swap.entities.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {}
