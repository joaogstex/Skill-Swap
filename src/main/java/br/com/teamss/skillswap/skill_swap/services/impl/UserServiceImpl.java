package br.com.teamss.skillswap.skill_swap.services.impl;

import br.com.teamss.skillswap.skill_swap.entities.Profile;
import br.com.teamss.skillswap.skill_swap.entities.Role;
import br.com.teamss.skillswap.skill_swap.entities.Skill;
import br.com.teamss.skillswap.skill_swap.entities.User;
import br.com.teamss.skillswap.skill_swap.repositories.ProfileRepository;
import br.com.teamss.skillswap.skill_swap.repositories.RoleRepository;
import br.com.teamss.skillswap.skill_swap.repositories.SkillRepository;
import br.com.teamss.skillswap.skill_swap.repositories.UserRepository;
import br.com.teamss.skillswap.skill_swap.services.UserService;
import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));
    }

    /**
     * public User save(User user) {
     * return userRepository.save(user);
     * }
     */

    @Override
    public User save(User user) {
        // tratar roles
        Set<Role> managedRoles = new HashSet<>();
        if (user.getRoles() != null) {
            for (Role role : user.getRoles()) {
                Role managedRole = roleRepository.findById(role.getRoleId())
                        .orElseThrow(() -> new EntityNotFoundException("Role não encontrada: " + role.getRoleId()));
                managedRoles.add(managedRole);
            }
        }
        user.setRoles(managedRoles);

        // tratar skills
        Set<Skill> managedSkills = new HashSet<>();
        if (user.getSkills() != null) {
            for (Skill skill : user.getSkills()) {
                Skill managedSkill = skillRepository.findById(skill.getSkillId())
                        .orElseThrow(() -> new EntityNotFoundException("Skill não encontrada: " + skill.getSkillId()));
                managedSkills.add(managedSkill);
            }
        }
        user.setSkills(managedSkills);

        // se for um perfil novo, salva ele primeiro para gerar id
        if (user.getProfile() != null) {
            if (user.getProfile().getProfileId() != null) {
                // perfil já existe, buscar pelo id
                // e associar ao usuário
                var managedProfile = profileRepository.findById(user.getProfile().getProfileId())
                        .orElseThrow(() -> new EntityNotFoundException(
                                "Profile não encontrado: " + user.getProfile().getProfileId()));
                user.setProfile(managedProfile);
            } else {
                profileRepository.save(user.getProfile());
            }
            user.getProfile().setUser(user);
        }

        return userRepository.save(user);
    }

    /**
     * public User update(UUID id, User userUpdate) {
     * User user = findById(id);
     * user.setUsername(userUpdate.getUsername());
     * user.setPassword(userUpdate.getPassword());
     * user.setRoles(userUpdate.getRoles());
     * user.setProfile(userUpdate.getProfile());
     * return userRepository.save(user);
     * }
     */
    @Override
    public User update(UUID id, User userUpdate) {
        User user = findById(id);

        //atualiza username e password simples
        user.setUsername(userUpdate.getUsername());
        user.setPassword(userUpdate.getPassword());

        //atualiza roles (buscando as gerenciadas no banco)
        Set<Role> managedRoles = new HashSet<>();
        if (userUpdate.getRoles() != null) {
            for (Role role : userUpdate.getRoles()) {
                Role managedRole = roleRepository.findById(role.getRoleId())
                        .orElseThrow(() -> new EntityNotFoundException("Role não encontrada: " + role.getRoleId()));
                managedRoles.add(managedRole);
            }
        }
        user.setRoles(managedRoles);

        //atualiza skills (buscando as gerenciadas no banco)
        Set<Skill> managedSkills = new HashSet<>();
        if (userUpdate.getSkills() != null) {
            for (Skill skill : userUpdate.getSkills()) {
                Skill managedSkill = skillRepository.findById(skill.getSkillId())
                        .orElseThrow(() -> new EntityNotFoundException("Skill não encontrada: " + skill.getSkillId()));
                managedSkills.add(managedSkill);
            }
        }
        user.setSkills(managedSkills);

        //atualiza perfil
        //se for um perfil novo, salva ele primeiro para gerar id
        if (userUpdate.getProfile() != null) {
            if (userUpdate.getProfile().getProfileId() != null) {
                Profile managedProfile = profileRepository.findById(userUpdate.getProfile().getProfileId())
                        .orElseThrow(() -> new EntityNotFoundException(
                                "Profile não encontrado: " + userUpdate.getProfile().getProfileId()));
                user.setProfile(managedProfile);
            } else {
                //perfil novo, salvar antes e associar
                profileRepository.save(userUpdate.getProfile());
                user.setProfile(userUpdate.getProfile());
            }
            user.getProfile().setUser(user);
        } else {
            user.setProfile(null);
        }

        return userRepository.save(user);
    }

    @Override
    public void delete(UUID id) {
        userRepository.deleteById(id);
    }
}
