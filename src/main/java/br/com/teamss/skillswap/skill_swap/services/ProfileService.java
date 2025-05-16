package br.com.teamss.skillswap.skill_swap.services;

import br.com.teamss.skillswap.skill_swap.entities.Profile;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProfileService {
    List<Profile> findAll();
    Optional<Profile> findById(Long id);
    Optional<Profile> findByUserId(UUID userId);
    Profile createProfile(Profile profile, UUID userId);
    Profile save(Profile profile);
    void delete(Long id);
}
