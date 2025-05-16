package br.com.teamss.skillswap.skill_swap.services;

import br.com.teamss.skillswap.skill_swap.entities.Profile;
import java.util.List;
import java.util.Optional;

public interface ProfileService {
    List<Profile> findAll();
    Optional<Profile> findById(Long id);
    Profile save(Profile profile);
    void delete(Long id);
}
