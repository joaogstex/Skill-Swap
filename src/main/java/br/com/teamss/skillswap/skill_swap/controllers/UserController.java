package br.com.teamss.skillswap.skill_swap.controllers;

import br.com.teamss.skillswap.skill_swap.dto.UserDTO;
import br.com.teamss.skillswap.skill_swap.entities.User;
import br.com.teamss.skillswap.skill_swap.services.UserService;
import br.com.teamss.skillswap.skill_swap.services.UserServiceDTO;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final UserServiceDTO userServiceDTO;

    public UserController(UserService userService, UserServiceDTO userServiceDTO) {
        this.userServiceDTO = userServiceDTO;
        this.userService = userService;
    }

    /**
     * @GetMapping
     *             public List<User> getAllUsers() {
     *             return userService.findAll();
     *             }
     * 
     *             @GetMapping("/{id}")
     *             public ResponseEntity<User> getUserById(@PathVariable UUID id) {
     *             return ResponseEntity.ok(userService.findById(id));
     *             }
     */

    @GetMapping
    public List<UserDTO> getAllUsers() {
        return userServiceDTO.findAllDTO();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable UUID id) {
        return ResponseEntity.ok(userServiceDTO.findByIdDTO(id));
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody User user) {
        User savedUser = userService.save(user);
        UserDTO userDTO = userServiceDTO.toUserDTO(savedUser);
        return ResponseEntity.ok(userDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable UUID id, @RequestBody User user) {
        user.setUserId(id);
        User updatedUser = userService.update(id, user);
        UserDTO userDTO = userServiceDTO.toUserDTO(updatedUser);
        return ResponseEntity.ok(userDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
