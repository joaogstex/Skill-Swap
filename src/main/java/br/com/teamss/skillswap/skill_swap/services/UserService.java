package br.com.teamss.skillswap.skill_swap.services;

import java.util.List;
import java.util.UUID;

import br.com.teamss.skillswap.skill_swap.entities.User;

public interface UserService {
    public List<User> findAll();
    public User findById(UUID id);
    public User save(User user);
    public User update(UUID id, User user);
    public void delete(UUID id);
}
