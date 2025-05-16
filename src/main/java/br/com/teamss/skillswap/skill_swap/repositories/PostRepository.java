package br.com.teamss.skillswap.skill_swap.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.teamss.skillswap.skill_swap.entities.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {}
