package br.com.teamss.skillswap.skill_swap.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.teamss.skillswap.skill_swap.dto.RoleDTO;
import br.com.teamss.skillswap.skill_swap.entities.Role;
import br.com.teamss.skillswap.skill_swap.services.RoleService;
import br.com.teamss.skillswap.skill_swap.services.RoleServiceDTO;

@RestController
@RequestMapping("/api/roles")
public class RoleController {
    
    private final RoleService roleService;
    private final RoleServiceDTO roleServiceDTO;

    public RoleController(RoleService roleService, RoleServiceDTO roleServiceDTO) {
        this.roleService = roleService;
        this.roleServiceDTO = roleServiceDTO;
    }

    @GetMapping
    public ResponseEntity<List<RoleDTO>> getAllRoles() {
        return ResponseEntity.ok(roleServiceDTO.findAllDTO());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleDTO> getRoleById(@PathVariable Long id) {
        return ResponseEntity.ok(roleServiceDTO.findByIdDTO(id));
    }

    @PostMapping
    public ResponseEntity<RoleDTO> createRole(@RequestBody Role role) {
        Role savedRole = roleService.save(role);
        RoleDTO roleDTO = roleServiceDTO.toRoleDTO(savedRole);
        return ResponseEntity.ok(roleDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoleDTO> updateRole(@PathVariable Long id, @RequestBody Role role) {
        role.setRoleId(id);
        Role updatedRole = roleService.save(role);
        RoleDTO roleDTO = roleServiceDTO.toRoleDTO(updatedRole);
        return ResponseEntity.ok(roleDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable Long id) {
        roleService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
