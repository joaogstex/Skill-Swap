package br.com.teamss.skillswap.skill_swap.services.impl;

import br.com.teamss.skillswap.skill_swap.entities.Profile;
import br.com.teamss.skillswap.skill_swap.repositories.ProfileRepository;
import br.com.teamss.skillswap.skill_swap.services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    public List<Profile> findAll() {
        return profileRepository.findAll();
    }

    public Optional<Profile> findById(Long id) {
        return profileRepository.findById(id);
    }

    public Profile save(Profile profile) {
        return profileRepository.save(profile);
    }

    public void delete(Long id) {
        profileRepository.deleteById(id);
    }
}
