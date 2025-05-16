package br.com.teamss.skillswap.skill_swap.repository;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.teamss.skillswap.skill_swap.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {}
