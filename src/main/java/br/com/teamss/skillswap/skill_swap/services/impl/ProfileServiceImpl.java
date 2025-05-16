package br.com.teamss.skillswap.skill_swap.services.impl;

import br.com.teamss.skillswap.skill_swap.entities.Profile;
import br.com.teamss.skillswap.skill_swap.entities.User;
import br.com.teamss.skillswap.skill_swap.repositories.ProfileRepository;
import br.com.teamss.skillswap.skill_swap.repositories.UserRepository;
import br.com.teamss.skillswap.skill_swap.services.ProfileService;
import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Profile> findAll() {
        return profileRepository.findAll();
    }

    public Optional<Profile> findById(Long id) {
        return profileRepository.findById(id);
    }

    public Optional<Profile> findByUserId(UUID userId) {
        return profileRepository.findByUser_UserId(userId);
    }

    public Profile createProfile(Profile profile, UUID userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));
        profile.setUser(user);
        user.setProfile(profile); //se user tiver o campo profile
        return profileRepository.save(profile);
    }

    public Profile save(Profile profile) {
        return profileRepository.save(profile);
    }

    public void delete(Long id) {
        profileRepository.deleteById(id);
    }
}
